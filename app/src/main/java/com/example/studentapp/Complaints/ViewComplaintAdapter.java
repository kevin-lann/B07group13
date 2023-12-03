package com.example.studentapp.Complaints;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.R;
import com.example.studentapp.objects.Complaint;
import com.example.studentapp.objects.ExtendedComplaint;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ViewComplaintAdapter extends RecyclerView.Adapter<ViewComplaintAdapter.ViewComplaintViewHolder> {

    public List<ExtendedComplaint> complaintList;
    private NavController navController;
    private DatabaseReference db = FirebaseDatabase
            .getInstance("https://b07-group13-default-rtdb.firebaseio.com")
            .getReference()
            .child("Complaints");

    public ViewComplaintAdapter(){

    }

    public ViewComplaintAdapter(NavController navController) {
        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_complaint_display, parent, false);
        return new ViewComplaintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewComplaintViewHolder holder, int position) {
        String currentId = complaintList.get(position).idInDatabase;
        ExtendedComplaint complaint = complaintList.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("key", currentId);

        holder.complaintTitle.setText(complaint.getComplaintTitle());
        holder.seeDetailButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                navController.navigate(R.id.action_viewComplaintsFragment_to_viewFullComplaintFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return complaintList != null ? complaintList.size() : 0;
    }


    static class ViewComplaintViewHolder extends RecyclerView.ViewHolder {
        TextView complaintTitle;
        Button seeDetailButton;
        Bundle complaintId;

        ViewComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            complaintTitle = itemView.findViewById(R.id.complaint_title);
            seeDetailButton = itemView.findViewById(R.id.see_detail);
        }


    }
}