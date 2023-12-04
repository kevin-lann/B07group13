package com.example.studentapp.ViewEventFeedback;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.Events.EventsUserAdapter;
import com.example.studentapp.R;
import com.example.studentapp.databinding.ViewFeedbackFragmentBinding;
import com.example.studentapp.objects.Event;
import com.example.studentapp.objects.UserFeedback;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ViewEventFeedbackFragment extends Fragment {
    private ViewFeedbackFragmentBinding binding;
    RecyclerView recyclerView;
    ViewEventFeedbackAdapter adapter;
    ArrayList<UserFeedback> feedbackList;
    private ViewEventFeedbackModel model;
    public static String eventId;
    public static String eventName;

    public ViewEventFeedbackFragment () {
        model = new ViewEventFeedbackModel(this);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = ViewFeedbackFragmentBinding.inflate(inflater, container, false);

        feedbackList = new ArrayList<>();

        recyclerView = binding.eventFeedbackList;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ViewEventFeedbackAdapter(getContext(), feedbackList, this);

        model.getEventFeedback(eventId).thenAccept( res -> {
           setupFeedbackList(res, recyclerView);

            binding.eventName.setText("Feedback for " + eventName);
            binding.ratingCount.setText("No. of feedback submissions: " + feedbackList.size());
            binding.ratingAvg.setText("Average event rating: " + getAvgRating() + " / 5.0 stars");
        });

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // set up arraylist of UserFeedback objects for the recyclerview from given
    // list of event ids.
    private void setupFeedbackList(@NonNull ArrayList<UserFeedback> feedbacks, RecyclerView rv) {
        feedbackList = feedbacks;
        rv.setAdapter(new ViewEventFeedbackAdapter(getContext(), feedbackList, this));
    }

    public ViewEventFeedbackModel getEventFeedbackModel() {
        return model;
    }

    public String getAvgRating() {
        double sum = 0;
        int cnt = 0;
        for(UserFeedback feedback : feedbackList) {
            sum += feedback.getRating();
            cnt++;
        }
        DecimalFormat format = new DecimalFormat("#.#");
        return format.format(sum / cnt);
    }
}
