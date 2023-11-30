package com.example.studentapp.Events;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentapp.R;
import com.example.studentapp.objects.Event;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EventsUserAdapter extends RecyclerView.Adapter<EventsUserAdapter.eventViewHolder>{

    private Context context;
    //private static FragmentManager fragmentManager;
    private ArrayList<Event> eventsList;
    private EventsModel model;

    public EventsUserAdapter(Context context, ArrayList<Event> eventsList, EventsModel model) {
        this.context = context;
        this.eventsList = eventsList;
        this.model = model;
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
        holder.eventInfo.setText("Where: " + event.eventLocation
                + " / When: " + event.getFormattedDate()
                + " @ " + event.getFormattedTime());
        holder.organizer.setText("Event organized by " + event.organizer);

        // RSVP For event
        holder.RSVP_Or_Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.eventRSVP(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    // Holds a view describing one instance of the recyclerview
    public static class eventViewHolder extends RecyclerView.ViewHolder{

        TextView eventName, organizer, description, eventInfo;
        Button RSVP_Or_Rate;

        public eventViewHolder(@NonNull View eventView) {
            super(eventView);
            eventName = eventView.findViewById(R.id.event_name);
            description = eventView.findViewById(R.id.event_description);
            eventInfo = eventView.findViewById(R.id.event_info);
            organizer = eventView.findViewById(R.id.organizer);
            RSVP_Or_Rate = eventView.findViewById(R.id.RSVP);
        }

    }

}
