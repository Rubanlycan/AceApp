package com.ionicframework.itech719214.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ionicframework.itech719214.Data;
import com.ionicframework.itech719214.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerListView extends RecyclerView.Adapter<RecyclerListView.RecyclerViewHolder> {


    private  List<Data> list;
    private Context context;


    public  RecyclerListView(List<Data> verticalList)
    {
        this.list = verticalList;

    }



    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View r_view = inflater.inflate(R.layout.view_all_item,parent,false);

        return new RecyclerViewHolder(r_view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.text_title.setText(list.get(i).getTitle());
        recyclerViewHolder.text_name.setText(list.get(i).getName());
        recyclerViewHolder.ratingBar.setRating(list.get(i).getRating());
        recyclerViewHolder.text_rating.setText(Float.toString(list.get(i).getRating()));
        Picasso.with(context).load("https://www.aceupdate.com/ACE_App2/CoverPage2/"+list.get(i).getImage()).into(recyclerViewHolder.img_issue);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class RecyclerViewHolder extends RecyclerView.ViewHolder  {


        ImageView img_issue;
        TextView text_title,text_name,text_rating,mag_title;
        RatingBar ratingBar;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            img_issue = itemView.findViewById(R.id.img_all);
            text_title = itemView.findViewById(R.id.text1);
            text_name = itemView.findViewById(R.id.text2);
            text_rating = itemView.findViewById(R.id.textrating);
            mag_title = itemView.findViewById(R.id.mag_title);
            ratingBar = itemView.findViewById(R.id.star_rating);


        }

    }



}
