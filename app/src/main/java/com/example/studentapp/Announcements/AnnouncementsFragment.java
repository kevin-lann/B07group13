package com.example.studentapp.Announcements;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.objects.Announcement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AnnouncementsFragment extends Fragment {

    RecyclerView recyclerView;
    List<Announcement> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.announcements_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        Log.w("annTest", "before load");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        Log.w("annTest", "after load");

        dataList = new ArrayList<>();

        MyAdapter adapter = new MyAdapter(getActivity(), dataList, this);
        recyclerView.setAdapter(adapter);
        Log.w("annTest", "adapter set");

        databaseReference = FirebaseDatabase.getInstance("https://b07-group13-default-rtdb.firebaseio.com")
                .getReference().child("Announcements");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    String id = itemSnapshot.getKey();
                    String name = String.valueOf(itemSnapshot.child("announcementName").getValue());
                    String desc = String.valueOf(itemSnapshot.child("announcementDescription").getValue());
                    String announcer = String.valueOf(itemSnapshot.child("announcer").getValue());

                    // parse time (string --> int[2] = {hour, min} )
                    String end = String.valueOf(itemSnapshot.child("time").getValue());
                    int[] time = {
                            Integer.parseInt(end.substring(0, end.indexOf(':'))),
                            Integer.parseInt(end.substring(end.indexOf(':') + 1, end.length()))
                    };

                    String dateStr = String.valueOf(itemSnapshot.child("date").getValue());
                    String date_arr[] = dateStr.split("-");

                    // parse date (month, day, year)
                    int[] date = {
                            Integer.parseInt(date_arr[0]),
                            Integer.parseInt(date_arr[1]),
                            Integer.parseInt(date_arr[2])
                    };

                    Announcement a = new Announcement(
                            Integer.parseInt(id),
                            announcer,
                            name,
                            desc,
                            time,
                            date
                    );

                    dataList.add(a);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
        return view;
    }
}
