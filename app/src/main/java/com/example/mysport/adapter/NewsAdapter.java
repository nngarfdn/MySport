package com.example.mysport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.mysport.R;
import com.example.mysport.model.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    public List<News> androidList;
    private Context mContext;
    private NewsAdapter.onSelectData onSelectData;

    public interface onSelectData {
        void onSelected(News mdlNews);
    }

    public NewsAdapter(Context context, List<News> android, NewsAdapter.onSelectData onSelectData) {
        this.mContext = context;
        this.androidList = android;
        this.onSelectData = onSelectData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_berita, viewGroup, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder viewHolder, int i) {

        final News berita = androidList.get(i);

        Glide.with(mContext)
                .load(berita.getUrlToImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_icon)
                //.transform(new CenterInside(), new RoundedCorners(30))
                .into(viewHolder.image);

        viewHolder.title.setText(berita.getTitle());
        viewHolder.publishedAt.setText(TimeUnits.getTimeAgo(berita.getPublisedAt()));
        viewHolder.cvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(berita);
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidList.size();
    }

}
