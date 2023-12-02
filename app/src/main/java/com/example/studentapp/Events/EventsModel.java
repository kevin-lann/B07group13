package com.example.studentapp.Events;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.studentapp.MainActivity;
import com.example.studentapp.objects.Event;
import com.example.studentapp.objects.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EventsModel {

    FirebaseDatabase db;
    private Long tmp_max_attendees;
    private Long tmp_num_attendees;
    private EventsFragment fragment;

    public EventsModel(EventsFragment fragment) {
        db = FirebaseDatabase.getInstance("https://b07-group13-default-rtdb.firebaseio.com");
        this.fragment = fragment;
    }

    /**
     * return arraylist of all of a given user's events
     * If user is AdminUser, then this returns their Created Events
     * If user is StudentUser, then this returns their RSVP Events
     */
    public CompletableFuture<ArrayList<String>> getUserEvents(User user) {

        DatabaseReference ref;
        CompletableFuture<ArrayList<String>> res = new CompletableFuture<>();

        String event_list_name = user.getUserType().equals("Admin") ? "Created_Events" : "RSVP_Events";

        ref = db.getReference().child("UserInfo").child(user.getUserType())
                .child(user.getUsername()).child(event_list_name);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> eventIds = new ArrayList<>();
                for(DataSnapshot id : snapshot.getChildren()) {
                    eventIds.add(id.getKey());
                }
                res.complete(eventIds);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                res.complete(null);
            }
        });
        return res;
    }

    /**
     * Get all recent events.
     * @return array list on all upcoming events in database
     */
    public CompletableFuture<ArrayList<String>> getAllEvents() {
        DatabaseReference ref;
        CompletableFuture<ArrayList<String>> res = new CompletableFuture<>();

        ref = db.getReference().child("Events");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> eventIds = new ArrayList<>();
                for(DataSnapshot id : snapshot.getChildren()) {
                    eventIds.add(id.getKey());
                }
                res.complete(eventIds);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                res.complete(null);
            }
        });
        return res;
    }

    /**
     * Retrieves event specified by id in the database
     */
    public CompletableFuture<Event> getEventFromId(String id) {
        CompletableFuture<Event> cf = new CompletableFuture<>();
        DatabaseReference query = db.getReference().child("Events").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.w("eventTest", "inside");
                Event event;
                String date = String.valueOf(snapshot.child("date").getValue());
                String event_description = String.valueOf(snapshot.child("event_description").getValue());
                String event_location = String.valueOf(snapshot.child("event_location").getValue());
                String event_name = String.valueOf(snapshot.child("event_name").getValue());
                String max_attendees = String.valueOf(snapshot.child("max_attendees").getValue());
                String num_attendees = String.valueOf(snapshot.child("num_attendees").getValue());
                String organizer = String.valueOf(snapshot.child("organizer").getValue());

                // parse start and end times (string --> int[2] = {hour, min} )
                String end = String.valueOf(snapshot.child("time_end").getValue());
                int[] time_end = {
                        Integer.parseInt(end.substring(0, end.indexOf(':'))),
                        Integer.parseInt(end.substring(end.indexOf(':') + 1, end.length()))
                };

                String srt = String.valueOf(snapshot.child("time_start").getValue());
                int[] time_start = {
                        Integer.parseInt(srt.substring(0, srt.indexOf(':'))),
                        Integer.parseInt(srt.substring(srt.indexOf(':') + 1, srt.length()))
                };

                String date_arr[] = date.split("-");

                // parse date (month, day, year)
                int[] event_date = {
                        Integer.parseInt(date_arr[0]),
                        Integer.parseInt(date_arr[1]),
                        Integer.parseInt(date_arr[2])
                };

                event = new Event(
                        Integer.parseInt(id),
                        organizer,
                        Integer.parseInt(num_attendees),
                        Integer.parseInt(max_attendees),
                        event_name,
                        event_description,
                        event_location,
                        time_start,
                        time_end,
                        event_date);

                cf.complete(event);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                cf.complete(null);
            }
        });

        return cf;
    }

    public void eventRSVP(Event event) {
        String id = new Integer(event.eventId).toString();
        String username = MainActivity.currUser.getUsername();

        alreadyRSVP(id).thenAccept( alrRSVP -> capacityReached(id).thenAccept(capReached -> {
            Log.w("eventTest", "check" + alrRSVP + " " + capReached);
            if(!alrRSVP && !capReached) {

                // Increment event num_attendees
                DatabaseReference ref1 = db.getReference().child("Events").child(id);
                ref1.child("num_attendees").setValue(tmp_num_attendees+1);

                // add event to user's RSVP list
                DatabaseReference ref2 = db.getReference().child("UserInfo")
                        .child("Student").child(username).child("RSVP_Events").child(id);
                ref2.setValue(event.eventName);
            }
            else {
                if(capReached) {
                    fragment.sendToast("Unable to RSVP - Capacity limit reached");
                }
                else if(alrRSVP) {
                    fragment.sendToast("Already RSVP'd");
                }
            }
        }));



    }

    /**
     * Checks if user is already RSVP'd for given event
     */
    private CompletableFuture<Boolean> alreadyRSVP(String id) {
        String username = MainActivity.currUser.getUsername();
        CompletableFuture<Boolean> res = new CompletableFuture<>();
        DatabaseReference query = db.getReference().child("UserInfo").child("Student")
                .child(username).child("RSVP_Events");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(id).exists()) {
                    res.complete(true);
                }
                res.complete(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                res.complete(null);
            }
        });
        return res;
    }

    /**
     * Checks if capacity reached for given event
     */
    private CompletableFuture<Boolean> capacityReached(String id) {
        CompletableFuture<Boolean> res = new CompletableFuture<>();
        DatabaseReference query = db.getReference().child("Events").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tmp_max_attendees = (Long)(snapshot.child("max_attendees").getValue());
                tmp_num_attendees = (Long)(snapshot.child("num_attendees").getValue());
                if (tmp_num_attendees.equals(tmp_max_attendees)) {
                    res.complete(true);
                }

                res.complete(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                res.complete(null);
            }
        });
        return res;
    }

}

