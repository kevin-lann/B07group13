package com.example.studentapp.NewEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.databinding.NewEventFragmentBinding;
import com.example.studentapp.objects.Event;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class NewEventFragment extends Fragment{

    private NewEventFragmentBinding binding;
    private NewEventModel model;
    private final int INPUT_MAX_LEN = 100;
    private final int EVENT_DESC_MAX_LEN = 1000;

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
                model.getEventId().thenAccept( res -> {
                    Event e = setupNewEvent(res);
                    if( e != null ) {
                        model.postEvent(e);
                        model.updateEventId(res + 1);
                        Toast.makeText(getContext(), "Created!", Toast.LENGTH_SHORT).show();
                    }
                });
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

    private Event setupNewEvent(long id) {

        String organizer = MainActivity.currUser.getUsername();
        String eventName = binding.eventName.getText().toString();
        String eventDescription = binding.eventDescription.getText().toString();
        String eventLocation = binding.eventLocation.getText().toString();

        String month = binding.eventMonth.getText().toString();
        String day = binding.eventDay.getText().toString();
        String year = binding.eventYear.getText().toString();
        String startHour = binding.eventStartHour.getText().toString();
        String startMinute = binding.eventStartMinute.getText().toString();
        String endHour = binding.eventEndHour.getText().toString();
        String endMinute = binding.eventEndMinute.getText().toString();
        int maxAttendees=Integer.parseInt(binding.eventMaxAttendees.getText().toString());

        // input sanitization
        if(eventName.length() > INPUT_MAX_LEN) {
            Toast.makeText(getContext(), "Event name length exceeded.", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(eventDescription.length() > EVENT_DESC_MAX_LEN) {
            Toast.makeText(getContext(), "Event description length exceeded.", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(eventLocation.length() > INPUT_MAX_LEN) {
            Toast.makeText(getContext(), "Event location length exceeded.", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(!isNumeric(month) || Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1) {
            Toast.makeText(getContext(), "Month value invalid.", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(!isNumeric(day) || Integer.parseInt(day) > 31 || Integer.parseInt(day) < 1) {
            Toast.makeText(getContext(), "Day value invalid.", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(!isNumeric(year) || Integer.parseInt(year) < 2023) {
            Toast.makeText(getContext(), "Year value invalid.", Toast.LENGTH_SHORT);
            return null;
        }
        if(!isNumeric(startHour) || Integer.parseInt(startHour) > 23 || Integer.parseInt(startHour) < 0) {
            Toast.makeText(getContext(), "Start hour value invalid.", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(!isNumeric(startMinute) || Integer.parseInt(startMinute) > 59 || Integer.parseInt(startMinute) < 0) {
            Toast.makeText(getContext(), "Start minute value invalid.", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(!isNumeric(endHour) || Integer.parseInt(endHour) > 23 || Integer.parseInt(endHour) < 0) {
            Toast.makeText(getContext(), "End hour value invalid.", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(!isNumeric(endMinute) || Integer.parseInt(endMinute) > 59 || Integer.parseInt(endMinute) < 0) {
            Toast.makeText(getContext(), "End minute value invalid.", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(Integer.parseInt(endHour) < Integer.parseInt(startHour)) {
            Toast.makeText(getContext(), "End time before start time. Invalid.", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(Integer.parseInt(endHour) == Integer.parseInt(startHour) &&
                Integer.parseInt(endMinute) < Integer.parseInt(startMinute)){
            Toast.makeText(getContext(), "End time before start time. Invalid.", Toast.LENGTH_SHORT).show();
            return null;
        }


        int[] eventDate = new int[] {
                Integer.parseInt(month),
                Integer.parseInt(day),
                Integer.parseInt(year)
        };
        int [] eventStart = new int[] {
                Integer.parseInt(startHour),
                Integer.parseInt(startMinute)
        };
        int [] eventEnd = new int[] {
                Integer.parseInt(endHour),
                Integer.parseInt(endMinute)
        };


        Event e = new Event((int) id,
                organizer,
                0,
                maxAttendees,
                eventName,
                eventDescription,
                eventLocation,
                eventStart,
                eventEnd,
                eventDate);

        return e;

    }

    private boolean isNumeric(String val) {
        try {
            Integer.parseInt(val);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

}
