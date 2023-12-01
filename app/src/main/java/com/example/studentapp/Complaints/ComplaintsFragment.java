package com.example.studentapp.Complaints;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.StudentDashboard.StudentDashboardFragment;
import com.example.studentapp.databinding.ComplaintsFragmentBinding;

public class ComplaintsFragment extends Fragment {

    private ComplaintsPresenter presenter;
    private ComplaintsFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ComplaintsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ComplaintsPresenter(new ComplaintsModel(), this);

        binding.buttonSubmit.setOnClickListener(v -> {
            String username = MainActivity.currUser.getUsername(); // [DONE] Replace with our username retrieval logic
            String title = binding.editComplaintTitle.getText().toString();
            String description = binding.editComplaintDesc.getText().toString();

            presenter.submitComplaint(username, title, description);
        });

        binding.buttonCancel.setOnClickListener(v -> {
            NavHostFragment.findNavController(ComplaintsFragment.this)
                    .navigate(R.id.action_SubmitComplaint_to_studentDashboardFragment);
        });
    }
    
    public void complaintSubmissionSuccess(String text) {
        Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
