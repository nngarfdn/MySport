package com.example.mysport.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysport.R;

import com.example.mysport.fragment.DetailAktivitas;
import com.example.mysport.fragment.TickerTimer;
import com.example.mysport.model.Aktivitas;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAktivitasAdapter extends RecyclerView.Adapter<ListAktivitasAdapter.ListViewHolder> {
    private ArrayList<Aktivitas> listAktivitas;
    private Context context;

    public ListAktivitasAdapter(ArrayList<Aktivitas> listAktivitas) {
        this.listAktivitas = listAktivitas;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aktivitas_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        final Aktivitas aktivitas = listAktivitas.get(position);
        Picasso.get()
                .load(aktivitas.getPhoto())
                .fit()
                .error(R.drawable.ic_launcher_background)
                .into(holder.photo);

        holder.name.setText(aktivitas.getName());
        holder.detail.setText(aktivitas.getDetail());
        holder.v_color.setBackgroundColor(aktivitas.getColor());

        holder.cv_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DetailAktivitas detailAktivitas = new DetailAktivitas();
                TickerTimer tickerTimer = new TickerTimer();
                //mengirim data
                Bundle mBundle = new Bundle();
                mBundle.putString("name", listAktivitas.get(position).getName());
                mBundle.putString("desc", listAktivitas.get(position).getDetail());
                mBundle.putString("photo", listAktivitas.get(position).getPhoto());
                mBundle.putInt("color", listAktivitas.get(position).getColor());
                mBundle.putString("key_id", listAktivitas.get(position).getKey_id());
                mBundle.putString("fav_status", listAktivitas.get(position).getFavStatus());
                detailAktivitas.setArguments(mBundle);
                tickerTimer.setArguments(mBundle);
                //pindah fragment sama seperti intent
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, detailAktivitas)
                        .addToBackStack(null).commit();
            }
        });
    }


    @Override
    public int getItemCount() {
        // jika rec view jadi berlipat ganda
        int limit = 4;
        return Math.min(listAktivitas.size(), limit);
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView name, detail;
        ImageView photo;
        RelativeLayout v_layout;
        View v_color;
        CardView cv_holder;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvNameDetail);
            detail = itemView.findViewById(R.id.tvDetailDesc);
            photo = itemView.findViewById(R.id.imgDetail);
            v_layout = itemView.findViewById(R.id.v_card);
            v_color = itemView.findViewById(R.id.v_color);
            cv_holder = itemView.findViewById(R.id.cv_holder);
        }
    }

}
