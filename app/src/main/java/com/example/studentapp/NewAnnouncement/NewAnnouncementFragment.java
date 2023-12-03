package com.example.studentapp.NewAnnouncement;

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
import com.example.studentapp.R;
import com.example.studentapp.databinding.NewAnnouncementFragmentBinding;
import com.example.studentapp.objects.AdminUser;
import com.example.studentapp.objects.Announcement;

import java.time.LocalDate;
import java.time.LocalTime;

public class NewAnnouncementFragment extends Fragment {
    private NewAnnouncementFragmentBinding binding;
    private NewAnnouncementModel model;

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
                Announcement a = setupNewAnnouncement();
                model.postAnnouncement(a);
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
    private Announcement setupNewAnnouncement() {
        // increment global annoucement id
        MainActivity.currAnnouncementId++;

        int id = MainActivity.currAnnouncementId;
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

        Log.w("announcementTest", "" + post_date[0] + post_date[1] + post_date[2] );

        Announcement a = new Announcement(id, announcer.getUsername(), announcementName, announcementDescription,
                post_time, post_date);

        return a;
    }
}
