package com.example.mysport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mysport.R;
import com.example.mysport.model.ArticlesItem;

import java.util.List;
import java.util.Objects;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    public List<ArticlesItem> androidList;
    private Context mContext;
    private NewsAdapter.onSelectData onSelectData;

    public interface onSelectData {
        void onSelected(ArticlesItem mdlNews);
    }

    public NewsAdapter(Context context, List<ArticlesItem> android, NewsAdapter.onSelectData onSelectData) {
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

        final ArticlesItem berita = androidList.get(i);

        Glide.with(mContext)
                .load(berita.getUrlToImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_icon)
                //.transform(new CenterInside(), new RoundedCorners(30))
                .into(viewHolder.image);

        viewHolder.title.setText(berita.getTitle());
        viewHolder.publishedAt.setText(TimeUnits.getTimeAgo(Objects.requireNonNull(berita.getPublishedAt())));
        viewHolder.cvNews.setOnClickListener(v -> onSelectData.onSelected(berita));
    }

    @Override
    public int getItemCount() {
        return androidList.size();
    }

}
