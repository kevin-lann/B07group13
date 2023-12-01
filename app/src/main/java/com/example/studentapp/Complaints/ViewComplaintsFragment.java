package com.example.studentapp.Complaints;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.R;
import com.example.studentapp.objects.Complaint;

import com.example.studentapp.objects.Complaint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewComplaintsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ViewComplaintAdapter complaintAdapter;
    private List<Complaint> complaintList;

    public ViewComplaintsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_complaints_fragment, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.complaints_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize Complaint List
        complaintList = getComplaints();

        // Initialize Adapter
        complaintAdapter = new ViewComplaintAdapter(complaintList);

        // Set Adapter to RecyclerView
        recyclerView.setAdapter(complaintAdapter);

        return view;
    }


    private List<Complaint> getComplaints() {
        final List<Complaint> complaints = new ArrayList<>();

        FirebaseDatabase db = FirebaseDatabase.getInstance();

        DatabaseReference complaintsRef = db.getReference("Complaints");

        complaintsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                complaints.clear(); // Clear the existing list to avoid duplicates

                // Iterate through all complaints in the dataSnapshot
                for (DataSnapshot complaintSnapshot : dataSnapshot.getChildren()) {
                    Complaint complaint = complaintSnapshot.getValue(Complaint.class);
                    complaints.add(complaint);
                }

                // Notify the adapter that data has changed
                complaintAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return complaints;
    }
}
