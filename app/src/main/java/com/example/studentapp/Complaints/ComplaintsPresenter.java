package com.example.studentapp.Complaints;

public class ComplaintsPresenter {

    private ComplaintsModel model;
    private ComplaintsFragment fragment;

    public ComplaintsPresenter(ComplaintsModel model, ComplaintsFragment fragment) {
        this.model = model;
        this.fragment = fragment;
    }

    public void submitComplaint(String username, String title, String description) {
        model.submitComplaintToFirebase(username, title, description);
        fragment.complaintSubmissionSuccess("Submitted Complaint Successfully!"); // Notify success to the fragment
    }
}
