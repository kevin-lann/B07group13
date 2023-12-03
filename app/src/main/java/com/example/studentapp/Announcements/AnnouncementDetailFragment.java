package com.example.studentapp.Announcements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.studentapp.R;

public class AnnouncementDetailFragment extends Fragment {

    TextView detailDesc, detailTitle, detailDate;
    ImageView detailImage;

    static String DataImage, DataTitle, DataDesc, DataDate, DataLang;

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.announcement_detail_fragment, container, false);

        detailDesc = view.findViewById(R.id.detailDesc);
        detailImage = view.findViewById(R.id.detailImage);
        detailTitle = view.findViewById(R.id.detailTitle);
        detailDate = view.findViewById(R.id.detailDate);

        detailDesc.setText("Announcement details below:\n" + DataDesc);
        detailTitle.setText(DataTitle);
        detailDate.setText("Posted on " + DataDate);
        //Glide.with(this).load(DataImage).into(detailImage);

        return view;
    }

}
