package com.example.studentapp.Events;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentapp.R;
import com.example.studentapp.objects.Event;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EventsUserAdapter extends RecyclerView.Adapter<EventsUserAdapter.eventViewHolder>{

    private Context context;
    //private static FragmentManager fragmentManager;
    private ArrayList<Event> eventsList;

    public EventsUserAdapter(Context context, ArrayList<Event> eventsList) {
        this.context = context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public eventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_display, parent, false);
        return new eventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull eventViewHolder holder, int position) {
        Event event = eventsList.get(position);
        holder.eventName.setText(event.eventName);
        holder.description.setText(event.eventDescription);
        holder.location.setText(event.eventLocation);
        holder.organizer.setText(event.organizer);
        // TO DO
        // Set onClickListener for card button so that can send to event page
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    // Holds a view describing one instance of the recyclerview
    public static class eventViewHolder extends RecyclerView.ViewHolder{

        TextView eventName, organizer, description, location, time, date;

        public eventViewHolder(@NonNull View eventView) {
            super(eventView);
            eventName = eventView.findViewById(R.id.event_name);
            description = eventView.findViewById(R.id.event_description);
            location = eventView.findViewById(R.id.event_location);
            organizer = eventView.findViewById(R.id.organizer);
        }

    }

}
