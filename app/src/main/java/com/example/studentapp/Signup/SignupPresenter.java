package com.example.studentapp.Signup;


import com.example.studentapp.MainActivity;
import com.example.studentapp.objects.AdminUser;
import com.example.studentapp.objects.StudentUser;
import com.example.studentapp.objects.User;

public class SignupPresenter {

    SignupModel model;
    SignupFragment frag;

    public SignupPresenter(SignupModel model, SignupFragment frag) {
        this.model = model;
        this.frag = frag;
    }

    public void performSignup(User user, boolean passwordConfirmed) {
        model.queryDB(user.getUsername(), user.getUserType()).thenAccept( success -> {
            if(success && passwordConfirmed) {
                // set main activity's curr user to new User
                if(user.getUserType().equals("Admin")) {
                    MainActivity.currUser = (AdminUser)user;
                    model.createNewUser((AdminUser)user);
                }
                else {
                    MainActivity.currUser = (StudentUser)user;
                    model.createNewUser((StudentUser)user);
                }
                frag.loginSuccess();
            }
            else {
                String msg = "Signup unsuccessful.\n" +
                        (passwordConfirmed ? "Username already exists." : "Password unconfirmed.");
                frag.loginUnsuccess(msg);
            }
        });
    }
}
