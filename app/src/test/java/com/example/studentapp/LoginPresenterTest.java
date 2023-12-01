package com.example.studentapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertNotNull;
import com.example.studentapp.Login.LoginFragment;
import com.example.studentapp.Login.LoginModel;
import com.example.studentapp.Login.LoginPresenter;

import java.util.concurrent.CompletableFuture;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class LoginPresenterTest {

    @Mock
    private LoginFragment view;

    @Mock
    private LoginModel model;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPerformLoginSuccess() {
        // Arrange
        when(model.queryDB(anyString(), anyString(), anyString()))
                .thenReturn(CompletableFuture.completedFuture(true));
        LoginPresenter presenter = new LoginPresenter(model, view);

        // Act
        presenter.performLogin("username", "password", "Admin");

        // Assert
        verify(view).loginSuccess();
        verify(view, never()).loginUnsuccess();
    }

    @Test
    public void testPerformLoginFailure() {
        // Arrange
        when(model.queryDB(anyString(), anyString(), anyString()))
                .thenReturn(CompletableFuture.completedFuture(false));
        LoginPresenter presenter = new LoginPresenter(model, view);

        // Act
        presenter.performLogin("username", "password", "Student");

        // Assert
        verify(view, never()).loginSuccess();
        verify(view).loginUnsuccess();
    }

    @Test
    public void testPerformLoginWithEmptyFields() {
        // Arrange
        LoginPresenter presenter = new LoginPresenter(model, view);

        // Act
        presenter.performLogin("", "", "Admin");

        // Assert
        verify(view, never()).loginSuccess();
        verify(view).loginUnsuccess();
    }

    @Test
    public void testNonNullModelAndView() {
        // Arrange & Act
        LoginPresenter presenter = new LoginPresenter(model, view);
        LoginModel model = new LoginModel();
        LoginFragment frag = new LoginFragment();

        // Assert
        assertNotNull(presenter);
        assertNotNull(model);
        assertNotNull(frag);
    }

    @Test
    public void testNotNullModelAndViewAfterLogin() {
        // Arrange
        when(model.queryDB(anyString(), anyString(), anyString()))
                .thenReturn(CompletableFuture.completedFuture(true));
        LoginPresenter presenter = new LoginPresenter(model, view);
        LoginModel model = new LoginModel();
        LoginFragment frag = new LoginFragment();

        // Act
        presenter.performLogin("username", "password", "Admin");

        // Assert
        assertNotNull(model);
        assertNotNull(frag);
    }
}