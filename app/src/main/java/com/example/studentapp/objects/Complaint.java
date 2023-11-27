package com.example.studentapp.objects;

public class Complaint {

    public int complaintID;
    public StudentUser student;
    public String complaintTitle;
    public String complaintDetails;

    public Complaint(int complaintID, StudentUser student){
        this.complaintID = complaintID;
        this.student = student;
    }

    public Complaint(String complaintTitle, String complaintDetails){
        this.complaintTitle = complaintTitle;
        this.complaintDetails = complaintDetails;
    }

    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof Complaint)) {
            return false;
        }
        Complaint c = (Complaint)other;
        return c.complaintID == this.complaintID;
    }

    @Override
    public int hashCode() {
        int result = 31, d = 67;
        result += d * complaintID;
        return result;
    }
}
