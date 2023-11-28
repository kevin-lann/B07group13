package com.example.studentapp.Complaints;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.studentapp.Login.LoginModel;
import com.example.studentapp.Login.LoginPresenter;
import com.example.studentapp.R;
import com.example.studentapp.Signup.SignupFragment;
import com.example.studentapp.databinding.ComplaintsFragmentBinding;
import com.example.studentapp.databinding.LoginFragmentBinding;
import com.example.studentapp.objects.AdminUser;
import com.example.studentapp.objects.Complaint;
import com.example.studentapp.objects.StudentUser;
import com.example.studentapp.objects.User;

public class ComplaintsFragment extends Fragment {

    private ComplaintsFragmentBinding binding;

    private ComplaintsPresenter presenter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        this.presenter = new ComplaintsPresenter(new ComplaintsModel(), this);

        binding = ComplaintsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Complaint complaint = setupComplaint();
                String enteredTitle = binding.editComplaintTitle.getText().toString();
                String enteredDescription = binding.editComplaintDesc.getText().toString();
                // presenter.performSignup(user, passwordConfirmed); # Do this
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private Complaint setupComplaint() {
        String title = binding.editComplaintTitle.getText().toString();
        String description = binding.editComplaintDesc.getText().toString();
        return new Complaint(title, description);
    }

    public void makeToast(String text) {
        Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void complaintsSuccessful() {
        NavHostFragment.findNavController(ComplaintsFragment.this)
                .navigate(R.id.action_SecondFragment_to_ThirdFragment);
    }


}
