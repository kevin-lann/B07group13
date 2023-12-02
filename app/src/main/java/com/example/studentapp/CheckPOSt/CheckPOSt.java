package com.example.studentapp.CheckPOSt;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.studentapp.R;
import com.example.studentapp.databinding.ActivityMainBinding;
import com.example.studentapp.databinding.CheckPostBinding;

public class CheckPOSt extends Fragment {
    private CheckPostBinding binding;
    Spinner spinner;
    EditText A31Input;
    EditText A67Input;
    EditText A22Input;
    EditText A37Input;
    EditText A48Input;
    TextView qualifyStatement;
    LinearLayout A31Layout;
    LinearLayout A67Layout;
    LinearLayout A22Layout;
    LinearLayout A37Layout;
    LinearLayout A48Layout;
    TextView A48Text;
    Button check;
    public CheckPOSt(){
        super(R.layout.check_post);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.check_post, container, false);

        return view;

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        A31Layout = view.findViewById(R.id.A31Layout);
        A67Layout = view.findViewById(R.id.A67Layout);
        A22Layout = view.findViewById(R.id.A22Layout);
        A37Layout = view.findViewById(R.id.A37Layout);
        A48Layout = view.findViewById(R.id.A48Layout);
        A48Text = view.findViewById(R.id.A48);

        A31Input = (EditText) view.findViewById(R.id.A31Input);
        A67Input = (EditText) view.findViewById(R.id.A67Input);
        A22Input = (EditText) view.findViewById(R.id.A22Input);
        A37Input = (EditText) view.findViewById(R.id.A37Input);
        A48Input = (EditText) view.findViewById(R.id.A48Input);
        qualifyStatement = (TextView) view.findViewById(R.id.qualifyStatement);

        check = (Button)view.findViewById(R.id.button);

        spinner = (Spinner) view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = spinner.getSelectedItem().toString();
                Log.i("item name", item);

                if(item.equals("Computer Science")){
                    A31Layout.setVisibility(View.VISIBLE);
                    A67Layout.setVisibility(View.VISIBLE);
                    A22Layout.setVisibility(View.VISIBLE);
                    A37Layout.setVisibility(View.VISIBLE);
                    A48Layout.setVisibility(View.VISIBLE);
                    A48Text.setText(getResources().getString(R.string.A48));
                }
                else if(item.equals("Mathematics Major") || item.equals("Mathematics Specialist")){
                    A31Layout.setVisibility(View.VISIBLE);
                    A67Layout.setVisibility(View.VISIBLE);
                    A22Layout.setVisibility(View.VISIBLE);
                    A37Layout.setVisibility(View.VISIBLE);
                    A48Layout.setVisibility(View.INVISIBLE);
                }
                else if(item.equals("Statistics Major")){
                    A31Layout.setVisibility(View.VISIBLE);
                    A67Layout.setVisibility(View.INVISIBLE);
                    A22Layout.setVisibility(View.VISIBLE);
                    A37Layout.setVisibility(View.VISIBLE);
                    A48Layout.setVisibility(View.VISIBLE);
                    A48Text.setText(getResources().getString(R.string.A08));
                }
                else if(item.equals("Statistics Specialist")){
                    A31Layout.setVisibility(View.VISIBLE);
                    A67Layout.setVisibility(View.VISIBLE);
                    A22Layout.setVisibility(View.VISIBLE);
                    A37Layout.setVisibility(View.VISIBLE);
                    A48Layout.setVisibility(View.VISIBLE);
                    A48Text.setText(getResources().getString(R.string.A08));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQualifyStatement();
            }
        });

//        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(CheckPOSt.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public boolean isValid(double gpa){
        return 0.0 <= gpa && gpa <= 4.0;
    }

    public void updateQualifyStatement() {
        String item = spinner.getSelectedItem().toString();
        boolean qualify = false;
        String A31Str = A31Input.getText().toString();
        String A22Str = A22Input.getText().toString();
        String A37Str = A37Input.getText().toString();
        double A31GPA = 0;
        double A22GPA = 0;
        double A37GPA = 0;
        double gpa;
        try {
            A31GPA = Double.parseDouble(A31Str);
            A22GPA = Double.parseDouble(A22Str);
            A37GPA = Double.parseDouble(A37Str);
        } catch (Exception e) {
            qualifyStatement.setText(getResources().getString(R.string.invalid));
            qualifyStatement.setTextColor(Color.parseColor("#FF0000"));
            return;
        }

        if(!isValid(A31GPA) || !isValid(A22GPA) || !isValid(A37GPA)){
            qualifyStatement.setText(getResources().getString(R.string.invalid));
            qualifyStatement.setTextColor(Color.parseColor("#FF0000"));
            return;
        }

        if (item.equals("Computer Science")) {
            String A67Str = A67Input.getText().toString();
            String A48Str = A48Input.getText().toString();
            double A67GPA = 0;
            double A48GPA = 0;

            try {
                A67GPA = Double.parseDouble(A67Str);
                A48GPA = Double.parseDouble(A48Str);
            } catch (Exception e) {
                qualifyStatement.setText(getResources().getString(R.string.invalid));
                qualifyStatement.setTextColor(Color.parseColor("#FF0000"));
                return;
            }

            if(!isValid(A67GPA) || !isValid(A48GPA)){
                qualifyStatement.setText(getResources().getString(R.string.invalid));
                qualifyStatement.setTextColor(Color.parseColor("#FF0000"));
                return;
            }

            gpa = (A67GPA + A48GPA + A22GPA + A37GPA + A31GPA) / 5;
            if (gpa >= 2.5 && A48GPA >= 3.0 &&
                    ((A67GPA >= 1.7 && A22GPA >= 1.7) || (A67GPA >= 1.7 && A37GPA >= 1.7) || (A37GPA >= 1.7 && A22GPA >= 1.7))) {
                qualify = true;
            }
        } else if (item.equals("Mathematics Major") || item.equals("Mathematics Specialist")) {
            String A67Str = A67Input.getText().toString();
            double A67GPA = 0;

            try {
                A67GPA = Double.parseDouble(A67Str);
            } catch (Exception e) {
                qualifyStatement.setText(getResources().getString(R.string.invalid));
                qualifyStatement.setTextColor(Color.parseColor("#FF0000"));
                return;
            }

            if(!isValid(A67GPA)){
                qualifyStatement.setText(getResources().getString(R.string.invalid));
                qualifyStatement.setTextColor(Color.parseColor("#FF0000"));
                return;
            }

            if (item.equals("Mathematics Major")) {
                gpa = (A22GPA + A31GPA + A37GPA + A67GPA) / 4;
                if (gpa >= 2.0 && (A22GPA >= 3.0 || A67GPA >= 3.0 || A37GPA >= 3.0)) {
                    qualify = true;
                }
            } else {
                gpa = (A22GPA + A31GPA + A37GPA + A67GPA) / 4;
                if (gpa >= 2.5 &&
                        ((A67GPA >= 3.0 && A22GPA >= 3.0) || (A67GPA >= 3.0 && A37GPA >= 3.0) || (A37GPA >= 3.0 && A22GPA >= 3.0))) {
                    qualify = true;
                }
            }
        }

        else if (item.equals("Statistics Major")) {
            String A08Str = A48Input.getText().toString();
            double A08GPA = 0;

            try {
                A08GPA = Double.parseDouble(A08Str);
            } catch (Exception e) {
                qualifyStatement.setText(getResources().getString(R.string.invalid));
                qualifyStatement.setTextColor(Color.parseColor("#FF0000"));
                return;
            }

            if(!isValid(A08GPA)){
                qualifyStatement.setText(getResources().getString(R.string.invalid));
                qualifyStatement.setTextColor(Color.parseColor("#FF0000"));
                return;
            }

            gpa = (A08GPA + A31GPA + A37GPA + A22GPA) / 4;
            if (gpa >= 2.3) {
                qualify = true;
            }
        } else if (item.equals("Statistics Specialist")) {
            String A67Str = A67Input.getText().toString();
            String A08Str = A48Input.getText().toString();
            double A67GPA = 0;
            double A08GPA = 0;

            try {
                A67GPA = Double.parseDouble(A67Str);
                A08GPA = Double.parseDouble(A08Str);
            } catch (Exception e) {
                qualifyStatement.setText(getResources().getString(R.string.invalid));
                qualifyStatement.setTextColor(Color.parseColor("#FF0000"));
                return;
            }

            if(!isValid(A67GPA) || !isValid(A08GPA)){
                qualifyStatement.setText(getResources().getString(R.string.invalid));
                qualifyStatement.setTextColor(Color.parseColor("#FF0000"));
                return;
            }

            gpa = (A08GPA + A22GPA + A37GPA + A31GPA + A67GPA) / 5;
            if (gpa >= 2.5) {
                qualify = true;
            }
        }


        if (qualify) {
            qualifyStatement.setText(getResources().getString(R.string.qualify));
            qualifyStatement.setTextColor(Color.parseColor("#008000"));
        } else {
            qualifyStatement.setText(getResources().getString(R.string.notQualify));
            qualifyStatement.setTextColor(Color.parseColor("#FF0000"));
        }
    }
}