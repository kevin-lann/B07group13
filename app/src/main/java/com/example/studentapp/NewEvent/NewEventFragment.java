package com.example.studentapp.NewEvent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.studentapp.MainActivity;
import com.example.studentapp.NewAnnouncement.NewAnnouncementModel;
import com.example.studentapp.R;
import com.example.studentapp.databinding.NewAnnouncementFragmentBinding;

public class NewEventFragment extends Fragment{

    private NewAnnouncementFragmentBinding binding;
    private NewAnnouncementModel model;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        this.model = new NewAnnouncementModel();

        binding = NewAnnouncementFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

}
