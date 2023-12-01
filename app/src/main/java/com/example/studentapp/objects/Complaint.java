package com.example.studentapp.objects;

public class Complaint {

    public String complaintTitle;
    public String complaintDetails;

    public Complaint(String complaintTitle, String complaintDetails) {
        this.complaintTitle = complaintTitle;
        this.complaintDetails = complaintDetails;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    public String getComplaintDetails() {
        return complaintDetails;
    }

    public void setComplaintDetails(String complaintDetails) {
        this.complaintDetails = complaintDetails;
    }
}
