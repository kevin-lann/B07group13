package com.example.studentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.studentapp.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CheckPOSt extends Fragment {

    public CheckPOSt(){
        super(R.layout.check_post);
    }
    private FragmentSecondBinding binding;
    Spinner spinner;
    List<String> items;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CheckPOSt.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        spinner = spinner.findViewById(R.id.spinner);

        items = new ArrayList<>();

        items.add("Statistics");
        items.add("Mathematics");
        items.add("Computer Science Minor");
        items.add("Computer Science Major/Specialist");

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,items));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}