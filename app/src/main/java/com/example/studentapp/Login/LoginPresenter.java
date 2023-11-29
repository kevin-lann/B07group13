package com.example.studentapp.Login;

import android.util.Log;

import com.example.studentapp.MainActivity;
import com.example.studentapp.objects.AdminUser;
import com.example.studentapp.objects.StudentUser;

public class LoginPresenter {

    LoginModel model;
    LoginFragment frag;

    public LoginPresenter(LoginModel model, LoginFragment frag) {
        this.model = model;
        this.frag = frag;
    }

    public void performLogin(String username, String password, String usertype) {
        model.queryDB(username, password, usertype).thenAccept( success -> {
            if(success) {
                // set main activity's curr user to new User
                if(usertype.equals("Admin")) {
                    MainActivity.currUser = new AdminUser(username, password);
                }
                else {
                    MainActivity.currUser = new StudentUser(username, password);
                }
                frag.loginSuccess();
            }
            else {
                frag.loginUnsuccess();
            }
        });
    }
}
