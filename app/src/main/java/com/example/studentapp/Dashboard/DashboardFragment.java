package com.example.studentapp.Dashboard;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import static androidx.constraintlayout.widget.Constraints.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.databinding.DashboardFragmentBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CompletableFuture;

public class DashboardFragment extends Fragment {

    private DashboardFragmentBinding binding;

    private DatabaseReference db;
    private int checkPostButtonAction;
    private long currentAnnouncementId;
    private long currentEventId;

    public DashboardFragment() {
        db = FirebaseDatabase
                .getInstance("https://b07-group13-default-rtdb.firebaseio.com")
                .getReference();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DashboardFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.w("dashTest", "first");
        setupDashboard(view);

        if(getActivity() != null){
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        getAnnouncementId().thenAccept(res1 -> {
            currentAnnouncementId = res1;
            db.child("Announcements").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (int i = 1; i < 4; i++) {
                        AnnouncementBox announcementBox = snapshot
                                .child(String.valueOf((int)currentAnnouncementId - i))
                                .getValue(AnnouncementBox.class);
                        if (announcementBox != null) {
                            if (i == 1) {
                                binding.announceTopic1.setText(announcementBox.getAnnouncementName());
                                binding.sender1.setText(announcementBox.getAnnouncer());
                                binding.date1.setText(announcementBox.getDate());
                            } else if (i == 2) {
                                binding.announceTopic2.setText(announcementBox.getAnnouncementName());
                                binding.sender2.setText(announcementBox.getAnnouncer());
                                binding.date2.setText(announcementBox.getDate());
                            } else {
                                binding.announceTopic3.setText(announcementBox.getAnnouncementName());
                                binding.sender3.setText(announcementBox.getAnnouncer());
                                binding.date3.setText(announcementBox.getDate());
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("Firebase", "Error reading data: " + error.getMessage());
                }
            });
        });
        getEventId().thenAccept(res2 -> {
            currentEventId = res2;
            System.out.println(currentEventId);
            db.child("Events").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (int j = 1; j < 4; j++) {
                        int iterativeEventId = (int)currentEventId - j;
                        EventBox eventBox = snapshot
                                .child(iterativeEventId + "")
                                .getValue(EventBox.class);
                        if (eventBox != null) {

                            if (j == 1) {
                                binding.eventTopic1.setText(eventBox.getEventName());
                                binding.sender4.setText(eventBox.getOrganizer());
                                binding.date4.setText(eventBox.getDate());
                                System.out.println(eventBox.eventName);
                                System.out.println(eventBox.organizer);
                                System.out.println(eventBox.date);
                            } else if (j == 2) {
                                binding.eventTopic2.setText(eventBox.getEventName());
                                binding.sender5.setText(eventBox.getOrganizer());
                                binding.date5.setText(eventBox.getDate());
                            } else {
                                binding.eventTopic3.setText(eventBox.getEventName());
                                binding.sender6.setText(eventBox.getOrganizer());
                                binding.date6.setText(eventBox.getDate());
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("Firebase", "Error reading data: " + error.getMessage());
                }
            });
        });


        /**
        //set the toolbar
        toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //make the title of the toolbar shows nothing
        getSupportActionBar().setTitle(null);
         **/

        binding.dashToolbar.dashSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Sign Out")
                        .setMessage("Are you sure you want to sign out?")
                        .setPositiveButton("Sign Out", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                NavHostFragment.findNavController(DashboardFragment.this)
                                        .navigate(R.id.action_dashboardFragment_to_LoginFragment);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });


        binding.seeAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_dashboardFragment_to_Announcements);
            }
        });

        binding.seeEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_studentDashboardFragment_to_Events);
            }
        });

        binding.newAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_dashboardFragment_to_newAnnouncement);
            }
        });

        binding.newEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_dashboardFragment_to_newEvent);
            }
        });

        binding.submitComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_studentDashboardFragment_to_SubmitComplaint);
            }
        });

        binding.checkPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(checkPostButtonAction);
            }
        });
    }

    public CompletableFuture<Long> getAnnouncementId() {
        CompletableFuture<Long> res1 = new CompletableFuture<>();
        final long[] currentAnnouncementId = new long[1];
        db.child("AnnouncementID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentAnnouncementId[0] = snapshot.getValue(Long.class);

                res1.complete(currentAnnouncementId[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                res1.complete(null);
            }
        });

        return res1;
    }

    public CompletableFuture<Long> getEventId() {
        CompletableFuture<Long> res2 = new CompletableFuture<>();
        final long[] currentEventId = new long[1];
        db.child("EventID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentEventId[0] = snapshot.getValue(Long.class);

                res2.complete(currentEventId[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                res2.complete(null);
            }
        });

        return res2;
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


            checkPostButtonAction = R.id.action_dashboardFragment_to_checkPOStFragment;
        }

        if(MainActivity.currUser.getUserType().equals("Admin")) {
            view.findViewById(R.id.dash_toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.admin_dash));
            getActivity().findViewById(R.id.toolbar).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.admin_dash));
            binding.dashToolbar.dashSignOutButton.setBackgroundTintList(getResources().getColorStateList(R.color.admin_dash));
            Log.w("dashTest", "setting visibility");
            binding.newAnnouncement.setVisibility(VISIBLE);
            binding.newEvent.setVisibility(VISIBLE);
            binding.submitComplaint.setVisibility(GONE);

            binding.checkPost.setText("View Complaints");

            checkPostButtonAction = R.id.action_dashboardFragment_to_viewComplaintsFragment;
        }
    }

}


