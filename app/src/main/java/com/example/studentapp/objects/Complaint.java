package com.example.studentapp.objects;

public class Complaint {
    public String id;
    public String complaintTitle;
    public String complaintDetails;

    public Complaint() {}

    public Complaint(String id, String complaintTitle, String complaintDetails) {
        this.id = id;
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
