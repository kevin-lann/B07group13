package com.example.studentapp.StudentDashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.Login.LoginFragment;
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
    private Toolbar toolbar;
    private Button seeAnnouncemnet;
    private Button seeEvent;
    private CardView announcement1;
    private TextView ann1Topic;
    private TextView ann1Sender;
    private TextView ann1Date;

    private StudentDashboardFragmentBinding binding;

    private DatabaseReference ann1Ref;

    public StudentDashboardFragment() {
        ann1Ref = FirebaseDatabase
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

        ann1Topic = binding.announceTopic1;
        ann1Sender = binding.sender1;
        ann1Date = binding.date1;

        seeAnnouncemnet = binding.seeAnnouncements;
        seeEvent = binding.seeEvents;

        announcement1 = binding.announcement1;

        /**
        //set the toolbar
        toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //make the title of the toolbar shows nothing
        getSupportActionBar().setTitle(null);
         **/

        seeAnnouncemnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Test2", Toast.LENGTH_SHORT).show();
            }
        });

        seeEvent.setOnClickListener(new View.OnClickListener() {
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

        binding.checkPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(StudentDashboardFragment.this)
                        .navigate(R.id.action_studentDashboardFragment_to_Events); // TODO replace
            }
        });

        announcement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "More details coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        ann1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AnnouncementBox announcement1 = snapshot.child("announcement1").getValue(AnnouncementBox.class);

                if (announcement1 != null) {
                    ann1Topic.setText(announcement1.getTopic());
                    ann1Sender.setText(announcement1.getSender());
                    ann1Date.setText(announcement1.getDate());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Failed to read value: " + error.toException(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
