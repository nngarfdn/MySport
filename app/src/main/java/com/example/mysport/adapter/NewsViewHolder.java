package com.example.mysport.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysport.R;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView title, publishedAt;
    public CardView cvNews;
    public View view;

    public NewsViewHolder(@NonNull View view) {
        super(view);

        image = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        publishedAt = view.findViewById(R.id.publishedAt);
        cvNews = view.findViewById(R.id.cvNews);

        this.view = view;

    }


}

