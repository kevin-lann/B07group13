package com.example.studentapp.ViewEventFeedback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.R;
import com.example.studentapp.objects.UserFeedback;

import java.util.ArrayList;

public class ViewEventFeedbackAdapter extends RecyclerView.Adapter<ViewEventFeedbackAdapter.FeedbackViewHolder>{

    private Context context;
    private ArrayList<UserFeedback> feedbackList;

    public ViewEventFeedbackAdapter(Context context, ArrayList<UserFeedback> feedbackList, ViewEventFeedbackFragment fragment) {
        this.context = context;
        this.feedbackList = feedbackList;
        this.fragment = fragment;
    }

    private ViewEventFeedbackFragment fragment;

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_feedback_display, parent, false);
        return new ViewEventFeedbackAdapter.FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        UserFeedback feedback = feedbackList.get(position);
        holder.feedbackDesc.setText("Feedback details: " + feedback.getFeedback());
        holder.feedbackRating.setText("Rating: " + feedback.getRating() + " / 5.0 stars");
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    // Holds a view describing one instance of the recyclerview
    public static class FeedbackViewHolder extends RecyclerView.ViewHolder{

        TextView feedbackDesc, feedbackRating;

        public FeedbackViewHolder(@NonNull View eventView) {
            super(eventView);
            feedbackDesc = eventView.findViewById(R.id.feedback_details);
            feedbackRating = eventView.findViewById(R.id.rating);
        }

    }

}
