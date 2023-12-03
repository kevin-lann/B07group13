package com.example.studentapp.objects;

public class ExtendedComplaint extends Complaint{

    public String submissionDate;
    public String submissionTime;
    public String idInDatabase;

    public ExtendedComplaint(){

    }
    public ExtendedComplaint(String id, String complaintTitle, String complaintDetails,
                             String submissionDate, String submissionTime, String idInDatabase){
        super(id, complaintTitle, complaintDetails);
        this.submissionDate = submissionDate;
        this.submissionTime = submissionTime;
        this.idInDatabase = idInDatabase;
    }
}
