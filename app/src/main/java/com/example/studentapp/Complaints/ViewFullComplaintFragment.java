package com.example.studentapp.Complaints;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.example.studentapp.R;
import com.example.studentapp.databinding.ViewFullComplaintsFragmentBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ViewFullComplaintFragment extends Fragment {
    private ViewFullComplaintsFragmentBinding binding;
    private DatabaseReference db;
    private ValueEventListener valueEventListener;
    private String complaintId;

    public ViewFullComplaintFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = FirebaseDatabase
                .getInstance("https://b07-group13-default-rtdb.firebaseio.com")
                .getReference()
                .child("Complaints");

        binding = ViewFullComplaintsFragmentBinding.inflate(inflater, container, false);

        complaintId = getArguments().getString("key");
        return binding .getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        valueEventListener = db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.fullComplaintTitle.setText(snapshot.child(complaintId)
                        .child("Title").getValue(String.class));
                binding.fullComplaintComplainer.setText(snapshot.child(complaintId)
                        .child("Username").getValue(String.class));
                binding.fullComplaintDate.setText(snapshot.child(complaintId)
                        .child("SubmissionDate").getValue(String.class) + " at " + snapshot
                        .child(complaintId).child("SubmissionTime").getValue(String.class));
                binding.fullComplaintDetail.setText(snapshot.child(complaintId)
                        .child("Description").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("ViewFullComplaintsFragment", "Error setting text", error.toException());
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                NavHostFragment.findNavController(ViewFullComplaintFragment.this)
                        .navigate(R.id.action_viewFullComplaintFragment_to_viewComplaintsFragment);
            }
        });

        binding.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                NavHostFragment.findNavController(ViewFullComplaintFragment.this)
                        .navigate(R.id.action_viewFullComplaintFragment_to_viewComplaintsFragment);
                db.child(complaintId).removeValue();
            }
        });

    }
}