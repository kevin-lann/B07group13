package com.example.studentapp.Complaints;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.R;
import com.example.studentapp.databinding.ViewComplaintsFragmentBinding;
import com.example.studentapp.objects.Complaint;

import com.example.studentapp.objects.Complaint;
import com.example.studentapp.objects.ExtendedComplaint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ViewComplaintsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ViewComplaintAdapter complaintAdapter;
    private ViewComplaintsFragmentBinding binding;

    private DatabaseReference db;
    private ValueEventListener valueEventListener;

    public ViewComplaintsFragment() {
        db = FirebaseDatabase
                .getInstance("https://b07-group13-default-rtdb.firebaseio.com")
                .getReference()
                .child("Complaints");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ViewComplaintsFragmentBinding.inflate(inflater, container, false);

        ActionBar actionBar = ((AppCompatActivity)requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle("All Complaints");
        }

        return binding.getRoot();
    }

    public void onViewCreated(@NotNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.complaints_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //System.out.println(complaintList.size());
        // Initialize Adapter
        complaintAdapter = new ViewComplaintAdapter(NavHostFragment.
                findNavController(ViewComplaintsFragment.this));

        // Initialize Complaint List
        getComplaints().thenAccept(res -> {complaintAdapter.complaintList = res;});

        // Set Adapter to RecyclerView
        recyclerView.setAdapter(complaintAdapter);

        binding.backToDashboard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                NavHostFragment.findNavController(ViewComplaintsFragment.this)
                        .navigate(R.id.action_viewComplaintsFragment_to_dashboardFragment);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Remove the ValueEventListener when the fragment is destroyed or the view is detached
        if (valueEventListener != null) {
            db.removeEventListener(valueEventListener);
        }
    }


    private CompletableFuture<ArrayList<ExtendedComplaint>> getComplaints() {
        ArrayList<ExtendedComplaint> complaints = new ArrayList<>();

        CompletableFuture<ArrayList<ExtendedComplaint>> res = new CompletableFuture<>();
        valueEventListener = db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                if(complaints != null){
                    complaints.clear(); // Clear the existing list to avoid duplicates
                }

                for (DataSnapshot complaintId : dataSnapshot.getChildren()){
                    ExtendedComplaint complaint = new ExtendedComplaint();
                    complaint.id = complaintId.child("Username").getValue(String.class);
                    complaint.complaintTitle = complaintId.child("Title").getValue(String.class);
                    complaint.complaintDetails = complaintId.child("Description").getValue(String.class);
                    complaint.submissionDate = complaintId.child("SubmissionDate").getValue(String.class);
                    complaint.submissionTime = complaintId.child("SubmissionTime").getValue(String.class);
                    complaint.idInDatabase = complaintId.getKey();

                    complaints.add(complaint);
                }

                if(complaints != null){
                    complaints.remove(complaints.size() - 1);
                }

                res.complete(complaints);

                // Notify the adapter that data has changed
                complaintAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ViewComplaintsFragment", "Error getting data", databaseError.toException());
            }
        });

        return res;
    }
}
