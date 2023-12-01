package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText feedbackEditText;
    private ConcreteFirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        ratingBar = findViewById(R.id.ratingBar);
        feedbackEditText = findViewById(R.id.feedbackEditText);
        Button submitButton = findViewById(R.id.button2);
        firebaseManager = new ConcreteFirebaseManager(); // Initialize FirebaseManager

        // Set click listener for the Submit Feedback button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the rating and feedback text
                float rating = ratingBar.getRating();
                String feedback = feedbackEditText.getText().toString().trim();

                // Check if rating and feedback are provided
                if (rating == 0.0f) {
                    Toast.makeText(MainActivity.this, "Please provide a rating", Toast.LENGTH_SHORT).show();
                } else if (feedback.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please provide feedback", Toast.LENGTH_SHORT).show();
                } else {
                    // Save feedback and rating to Firebase
                    firebaseManager.saveFeedbackAndRating(rating, feedback);

                    // For demonstration purposes, just show a Toast message
                    String message = "Rating: $rating\nFeedback: $feedback\nSaved to Firebase";
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
