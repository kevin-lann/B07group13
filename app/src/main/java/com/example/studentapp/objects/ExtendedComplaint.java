package com.example.studentapp.objects;

public class ExtendedComplaint extends Complaint{

    public String submissionDate;
    public String submissionTime;

    public ExtendedComplaint(){

    }
    public ExtendedComplaint(String id, String complaintTitle, String complaintDetails,
                             String submissionDate, String submissionTime){
        super(id, complaintTitle, complaintDetails);
        this.submissionDate = submissionDate;
        this.submissionTime = submissionTime;
    }
}
