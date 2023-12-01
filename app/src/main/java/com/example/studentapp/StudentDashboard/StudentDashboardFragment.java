package com.example.studentapp.StudentDashboard;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.databinding.StudentDashboardFragmentBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

public class StudentDashboardFragment extends Fragment {

    private StudentDashboardFragmentBinding binding;

    private DatabaseReference db;

    private int checkPostButtonAction;

    public StudentDashboardFragment() {
        db = FirebaseDatabase
                .getInstance("https://b07-group13-default-rtdb.firebaseio.com")
                .getReference()
                .child("Announcements");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = StudentDashboardFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupDashboard(view);

        /**
        //set the toolbar
        toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //make the title of the toolbar shows nothing
        getSupportActionBar().setTitle(null);
         **/

        binding.seeAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Test2", Toast.LENGTH_SHORT).show();
            }
        });

        binding.seeEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(StudentDashboardFragment.this)
                        .navigate(R.id.action_studentDashboardFragment_to_Events);
            }
        });

        binding.submitComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(StudentDashboardFragment.this)
                        .navigate(R.id.action_studentDashboardFragment_to_SubmitComplaint);
            }
        });

        // action depending on checkPostButtonAction
        binding.checkPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(StudentDashboardFragment.this)
                        .navigate(checkPostButtonAction);
            }
        });

        binding.announcement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "More details coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AnnouncementBox announcement1 = snapshot.child("announcement1").getValue(AnnouncementBox.class);

                if (announcement1 != null) {
                    binding.announceTopic1.setText(announcement1.getTopic());
                    binding.sender1.setText(announcement1.getSender());
                    binding.date1.setText(announcement1.getDate());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Failed to read value: " + error.toException(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * Make appropriate changes to dashboard depending on if user is admin or student
     */
    private void setupDashboard(View view) {
        if(MainActivity.currUser.getUserType().equals("Student")) {
            view.findViewById(R.id.dash_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.student_dash));
            getActivity().findViewById(R.id.toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.student_dash));
            binding.newAnnouncement.setVisibility(GONE);
            binding.newEvent.setVisibility(GONE);
            binding.submitComplaint.setVisibility(VISIBLE);

            binding.checkPost.setText("Check POSt");

            // TODO uncomment when checkPost ready
            // checkPostButtonAction = R.id.action_studentDashboardFragment_to_checkPost;
        }

        if(MainActivity.currUser.getUserType().equals("Admin")) {
            view.findViewById(R.id.dash_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.admin_dash));
            getActivity().findViewById(R.id.toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.admin_dash));

            binding.newAnnouncement.setVisibility(VISIBLE);
            binding.newEvent.setVisibility(VISIBLE);
            binding.submitComplaint.setVisibility(GONE);

            binding.checkPost.setText("View Complaints");

            // TODO uncomment when view complaints ready
            //checkPostButtonAction = R.id.action_studentDashBoardFragment_to_viewComplaints;
        }
    }

}


