package com.example.studentapp.RateEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.studentapp.MainActivity;
import com.example.studentapp.databinding.DashboardFragmentBinding;
import com.example.studentapp.databinding.RateEventFragmentBinding;
import com.example.studentapp.objects.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RateEventFragment extends Fragment {

    private RatingBar ratingBar;
    private EditText feedbackEditText;
    private ConcreteFirebaseManager firebaseManager;

    private RateEventFragmentBinding binding;

    public static Event event;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = RateEventFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view,Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        ratingBar = binding.ratingBar;
        feedbackEditText = binding.feedbackEditText;
        Button submitButton = binding.button2;
        firebaseManager = new ConcreteFirebaseManager(); // Initialize FirebaseManager

        // Set text of event name
        binding.EventName.setText(event.eventName);

        // Set click listener for the Submit Feedback button
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Get the rating and feedback text
                float rating = ratingBar.getRating();
                String feedback = feedbackEditText.getText().toString().trim();

                // Check if rating and feedback are provided
                if (rating == 0.0f) {
                    Toast.makeText(getActivity(), "Please provide a rating", Toast.LENGTH_SHORT).show();
                } else if (feedback.isEmpty()) {
                    Toast.makeText(getActivity(), "Please provide feedback", Toast.LENGTH_SHORT).show();
                } else {
                    // Save feedback and rating to Firebase
                    firebaseManager.saveFeedbackAndRating(rating, feedback, event.eventId);

                    // For demonstration purposes, just show a Toast message
                    String message = "Rating: " + rating + "\nFeedback: " + feedback + "\nSaved to Firebase";
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
