package com.example.studentapp.Complaints;

import com.example.studentapp.Login.LoginFragment;
import com.example.studentapp.Login.LoginModel;
import com.example.studentapp.MainActivity;
import com.example.studentapp.objects.AdminUser;
import com.example.studentapp.objects.StudentUser;
import com.example.studentapp.objects.User;

public class ComplaintsPresenter {

    ComplaintsModel model;
    ComplaintsFragment frag;

    public ComplaintsPresenter(ComplaintsModel model, ComplaintsFragment frag) {
        this.model = model;
        this.frag = frag;
    }

    public void createComplaint(User user) {

    }

}
