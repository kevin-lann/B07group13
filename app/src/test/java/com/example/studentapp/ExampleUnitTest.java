package com.example.studentapp;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.studentapp.Signup.SignupModel;
import com.example.studentapp.objects.StudentUser;
import com.example.studentapp.objects.User;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testAddUser() {
        User user = new StudentUser("bob", "pass");
        SignupModel model = new SignupModel();
        model.createNewUser(user);
    }
}