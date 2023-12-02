package com.example.studentapp.Complaints;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.R;
import com.example.studentapp.objects.Complaint;
import com.example.studentapp.objects.ExtendedComplaint;

import java.util.List;

public class ViewComplaintAdapter extends RecyclerView.Adapter<ViewComplaintAdapter.ViewComplaintViewHolder> {

    public List<ExtendedComplaint> complaintList;

    public ViewComplaintAdapter(){

    }

    public ViewComplaintAdapter(List<ExtendedComplaint> complaintList) {
        this.complaintList = complaintList;
    }

    @NonNull
    @Override
    public ViewComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_complaint_display, parent, false);
        return new ViewComplaintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewComplaintViewHolder holder, int position) {
        ExtendedComplaint complaint = complaintList.get(position);
        holder.complaintTitle.setText(complaint.getComplaintTitle());
        holder.complainer.setText(complaint.getComplaintDetails());
    }

    @Override
    public int getItemCount() {
        return complaintList != null ? complaintList.size() : 0;
    }


    static class ViewComplaintViewHolder extends RecyclerView.ViewHolder {
        TextView complaintTitle;
        TextView complainer;

        ViewComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            complaintTitle = itemView.findViewById(R.id.complaint_title);
            complainer = itemView.findViewById(R.id.complainer);
        }


    }
}