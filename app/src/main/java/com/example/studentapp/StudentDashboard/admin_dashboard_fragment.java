package com.example.studentapp.StudentDashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.Login.LoginFragment;
import com.example.studentapp.R;
import com.example.studentapp.databinding.AdminDashboardFragmentBinding;
import com.example.studentapp.databinding.StudentDashboardFragmentBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

public class admin_dashboard_fragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private AdminDashboardFragmentBinding binding;
    public admin_dashboard_fragment() {
    }

    public static admin_dashboard_fragment newInstance(String param1, String param2) {
        admin_dashboard_fragment fragment = new admin_dashboard_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = AdminDashboardFragmentBinding.inflate(inflater, container,false);

        //To make action bar disappear
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Administrator");
        requireActivity().setTheme(R.style.AdminDashboardTheme);

        return binding.getRoot();
    }

}