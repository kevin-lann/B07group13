package com.example.studentapp.Announcements;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.R;
import com.bumptech.glide.Glide;
import com.example.studentapp.objects.Announcement;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Announcement> dataList;
    private AnnouncementsFragment fragment;

    public MyAdapter(Context context, List<Announcement> dataList, AnnouncementsFragment fragment) {
        this.context = context;
        this.dataList = dataList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int pos = position;
        //Glide.with(context).load("drawable/uploadimg.png").into(holder.recImage);
        holder.recTitle.setText(dataList.get(pos).announcementName);

        String desc = dataList.get(pos).announcementDescription;
        desc =  desc.length() > 30 ? desc + "..." : desc;
        holder.recDesc.setText(desc);

        holder.recLang.setText("Announcement sent by " + dataList.get(pos).announcer);

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //AnnouncementDetailFragment.DataImage = "drawable/uploadimg.png";
                AnnouncementDetailFragment.DataDesc = dataList.get(pos).announcementDescription;
                AnnouncementDetailFragment.DataTitle = dataList.get(pos).announcementName;
                AnnouncementDetailFragment.DataDate = dataList.get(pos).getFormattedDate() + ", at " + dataList.get(pos).getFormattedTime();

                // AnnouncementDetailFragment.DataKey = dataList.get(holder.getAdapterPosition()).getKey();
                AnnouncementDetailFragment.DataLang = dataList.get(pos).announcer;

                NavHostFragment.findNavController(fragment)
                        .navigate(R.id.action_announcements_to_details);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }*/
}

class MyViewHolder extends RecyclerView.ViewHolder{

    //ImageView recImage;
    TextView recTitle, recDesc, recLang;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        //recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recPriority);
        recTitle = itemView.findViewById(R.id.recTitle);
    }
}
