package com.example.studentapp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import com.example.studentapp.Login.LoginFragment;
import com.example.studentapp.Login.LoginModel;
import com.example.studentapp.Login.LoginPresenter;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    LoginModel mockedModel;

    @Mock
    LoginFragment mockedView;

    @Test
    public void testPerformLoginWithNonExistingUser() {
        when(mockedModel.queryDB(anyString(), anyString(), anyString()))
                .thenReturn(CompletableFuture.completedFuture(false));

        LoginPresenter presenter = new LoginPresenter(mockedModel, mockedView);
        presenter.performLogin("nonexistinguser", "nonexistingpassword", "Student");

        verify(mockedView).loginUnsuccess();
    }

    @Test
    public void testPerformLoginWithExistingStudentUser() {
        when(mockedModel.queryDB(anyString(), anyString(), anyString()))
                .thenReturn(CompletableFuture.completedFuture(true));

        LoginPresenter presenter = new LoginPresenter(mockedModel, mockedView);
        presenter.performLogin("existingstudent", "existingpassword", "Student");

        verify(mockedView).loginSuccess();
    }

    @Test
    public void testPerformLoginWithExistingAdminUser() {
        when(mockedModel.queryDB(anyString(), anyString(), anyString()))
                .thenReturn(CompletableFuture.completedFuture(true));

        LoginPresenter presenter = new LoginPresenter(mockedModel, mockedView);
        presenter.performLogin("existingadmin", "existingpassword", "Admin");

        verify(mockedView).loginSuccess();
    }

    @Test
    public void testPerformLoginWithEmptyFields() {
        LoginPresenter presenter = new LoginPresenter(mockedModel, mockedView);
        presenter.performLogin("", "", "Student");

        verify(mockedView).loginUnsuccess();
    }

    @Test
    public void testPerformLoginWithInvalidCredentials() {
        when(mockedModel.queryDB(anyString(), anyString(), anyString()))
                .thenReturn(CompletableFuture.completedFuture(false));

        LoginPresenter presenter = new LoginPresenter(mockedModel, mockedView);
        presenter.performLogin("invaliduser", "invalidpassword", "Student");

        verify(mockedView).loginUnsuccess();
    }
}