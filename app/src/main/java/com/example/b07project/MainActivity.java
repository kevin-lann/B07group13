package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button seeAnnouncemnet;
    private Button seeEvent;
    private CardView announcement1;
    private TextView ann1Topic;
    private TextView ann1Sender;
    private TextView ann1Date;

    private DatabaseReference ann1Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //在程序编译的时候这个方法会把layout（基本就是整个页面的框架）读出来
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        ann1Ref = FirebaseDatabase.getInstance().getReference().child("Announcements");

        ann1Topic = findViewById(R.id.announceTopic1);
        ann1Sender = findViewById(R.id.sender1);
        ann1Date = findViewById(R.id.date1);

        seeAnnouncemnet = findViewById(R.id.seeAnnouncements);
        seeEvent = findViewById(R.id.seeEvents);

        announcement1 = findViewById(R.id.announcement1);

        //set the toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //make the title of the toolbar shows nothing
        getSupportActionBar().setTitle(null);

        seeAnnouncemnet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "Hello Armaan!", Toast.LENGTH_SHORT).show();
            }
        });

        seeEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "a", Toast.LENGTH_SHORT).show();
            }
        });

        announcement1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "More details coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        ann1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Announcement announcement1 = snapshot.child("announcement1").getValue(Announcement.class);

                if (announcement1 != null){
                    ann1Topic.setText(announcement1.getTopic());
                    ann1Sender.setText(announcement1.getSender());
                    ann1Date.setText(announcement1.getDate());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to read value: " + error.toException(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

}