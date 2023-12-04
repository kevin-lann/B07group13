package com.example.studentapp.NewAnnouncement;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.databinding.NewAnnouncementFragmentBinding;
import com.example.studentapp.objects.AdminUser;
import com.example.studentapp.objects.Announcement;

import java.time.LocalDate;
import java.time.LocalTime;

public class NewAnnouncementFragment extends Fragment {
    private NewAnnouncementFragmentBinding binding;
    private NewAnnouncementModel model;
    private final int ANNOUNCEMENT_MAX_LEN = 100;
    private final int ANNOUNCEMENT_DESC_MAX_LEN = 1000;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        this.model = new NewAnnouncementModel();

        binding = NewAnnouncementFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.getAnnouncementId().thenAccept( res -> {
                        Announcement a = setupNewAnnouncement(res);
                        if (a != null) {
                            model.postAnnouncement(a);
                            model.updateAnnouncementId(res + 1);
                        }
                });
            }
        });

        binding.buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewAnnouncementFragment.this)
                        .navigate(R.id.action_testFragment_to_Login);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("NewApi")
    private Announcement setupNewAnnouncement(long id) {

        AdminUser announcer = (AdminUser) MainActivity.currUser;
        String announcementName = binding.textAnnouncementHeadline.getText().toString();
        String announcementDescription = binding.editTextAnnouncementText.getText().toString();

        // parse start time (string --> int[2] = {hour, min} )

        String time = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            time = LocalTime.now().toString();
        }
        int[] post_time = {
                Integer.parseInt(time.substring(0, time.indexOf(':'))),
                Integer.parseInt(time.substring(time.indexOf(':') + 1, 5))
        };

        // parse date (month, day, year)

        String date = null;
        date = LocalDate.now().toString();
        String date_arr[] = date.split("-");

        int[] post_date = {
                Integer.parseInt(date_arr[1]),
                Integer.parseInt(date_arr[2]),
                Integer.parseInt(date_arr[0])
        };

        // Perform user input validity checks
        if(announcementName.length() > ANNOUNCEMENT_MAX_LEN) {
            Toast.makeText(getContext(), "Announcement headline length exceeded.", Toast.LENGTH_SHORT);
            return null;
        }
        if(announcementName.length() > ANNOUNCEMENT_MAX_LEN) {
            Toast.makeText(getContext(), "Announcement headline length exceeded.", Toast.LENGTH_SHORT);
            return null;
        }

        Announcement a = new Announcement((int) id, announcer.getUsername(), announcementName, announcementDescription,
                post_time, post_date);

        return a;
    }

    public void setupSelection() {

    }
}
