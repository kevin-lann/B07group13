package com.example.studentapp.Complaints;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.studentapp.Login.LoginModel;
import com.example.studentapp.Login.LoginPresenter;
import com.example.studentapp.R;
import com.example.studentapp.databinding.ComplaintsFragmentBinding;
import com.example.studentapp.databinding.LoginFragmentBinding;
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


    }
}
