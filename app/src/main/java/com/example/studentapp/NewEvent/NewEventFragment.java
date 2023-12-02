package com.example.studentapp.NewEvent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.studentapp.MainActivity;
import com.example.studentapp.NewAnnouncement.NewAnnouncementFragment;
import com.example.studentapp.NewAnnouncement.NewAnnouncementModel;
import com.example.studentapp.R;
import com.example.studentapp.databinding.NewAnnouncementFragmentBinding;
import com.example.studentapp.databinding.NewEventFragmentBinding;
import com.example.studentapp.objects.AdminUser;
import com.example.studentapp.objects.Announcement;
import com.example.studentapp.objects.Event;

public class NewEventFragment extends Fragment{

    private NewEventFragmentBinding binding;
    private NewEventModel model;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        this.model = new NewEventModel();

        binding = NewEventFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.eventCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event e = setupNewEvent();
                model.postEvent(e);
            }
        });

        binding.eventCancelCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewEventFragment.this)
                        .navigate(R.id.action_newEventFragment_to_Dashboard);
            }
        });
    }

    private Event setupNewEvent() {
        // increment global event id
        MainActivity.currEventId++;

        int id = MainActivity.currEventId;
        String organizer = MainActivity.currUser.getUsername();
        String eventName = binding.eventName.getText().toString();
        String eventDescription = binding.eventDescription.getText().toString();
        String eventLocation = binding.eventLocation.getText().toString();
        int[] eventDate = new int[] {
                Integer.parseInt(binding.eventMonth.getText().toString()),
                Integer.parseInt(binding.eventDay.getText().toString()),
                Integer.parseInt(binding.eventYear.getText().toString())
        };
        int [] eventStart = new int[] {
                Integer.parseInt(binding.eventStartHour.getText().toString()),
                Integer.parseInt(binding.eventStartMinute.getText().toString())
        };
        int [] eventEnd = new int[] {
                Integer.parseInt(binding.eventEndHour.getText().toString()),
                Integer.parseInt(binding.eventEndMinute.getText().toString())
        };
        int maxAttendees=Integer.parseInt(binding.eventMaxAttendees.getText().toString());

        Event e = new Event(id, organizer, 0, maxAttendees, eventName, eventDescription, eventLocation,
                eventStart, eventEnd, eventDate);

        return e;

    }

}
