package com.example.studentapp.Signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.studentapp.Login.LoginFragment;
import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.databinding.SignupFragmentBinding;
import com.example.studentapp.objects.AdminUser;
import com.example.studentapp.objects.StudentUser;
import com.example.studentapp.objects.User;

public class SignupFragment extends Fragment {

    private SignupFragmentBinding binding;

    private SignupPresenter presenter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        this.presenter = new SignupPresenter(new SignupModel(), this);

        binding = SignupFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = setupUser();
                String enteredPassword = binding.editTextTextPassword.getText().toString();
                String enteredConfirmPassword = binding.editTextTextConfirmPassword.getText().toString();
                boolean passwordConfirmed = (enteredPassword.equals(enteredConfirmPassword));
                presenter.performSignup(user, passwordConfirmed);
            }
        });

        binding.buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SignupFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private User setupUser() {
        String username = binding.editTextTextUsername.getText().toString();
        String password = binding.editTextTextPassword.getText().toString();
        if(binding.switch1.isChecked()) {
            return new AdminUser(username, password);
        }
        else {
            return new StudentUser(username, password);
        }
    }

    public void makeToast(String text) {
        Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void loginSuccess() {
        NavHostFragment.findNavController(SignupFragment.this)
                .navigate(R.id.action_SecondFragment_to_dashboard);
    }

    public void loginUnsuccess(String msg) {
        makeToast(msg);
    }

}