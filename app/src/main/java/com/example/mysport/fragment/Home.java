package com.example.mysport.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.mysport.R;
import com.example.mysport.adapter.ListAktivitasAdapter;
import com.example.mysport.model.Aktivitas;
import com.example.mysport.model.AktivitasData;
import java.util.ArrayList;
import java.util.Calendar;

public class Home extends Fragment {
    private ViewFlipper viewFlipper;
    private TextView tanggal;
    private RecyclerView rvAktivitas;
    private ArrayList<Aktivitas> list = new ArrayList<>();
    private Context context;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        initImage();
        getCalendar();
        return view;
    }

    private void getCalendar() {
        Calendar calendar = Calendar.getInstance();
        int hari = calendar.get(Calendar.DAY_OF_MONTH);
        int bulan = calendar.get(Calendar.MONTH);
        int tahun = calendar.get(Calendar.YEAR);
        String finalTanggal = hari + "-" + bulan + "-" + tahun;
        tanggal.setText(finalTanggal);
    }

    private void flipperImages(int images) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(images);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        //TODO animation
    }

    private void initImage() {
        int[] images = {R.drawable.flipi, R.drawable.flipii, R.drawable.flipiii, R.drawable.flipiv};

        // input to flipper
        for (int image : images) {
            flipperImages(image);
        }
    }

    private void initViews(View view) {
        viewFlipper = view.findViewById(R.id.v_flipper);
        tanggal = view.findViewById(R.id.tanggal);
        rvAktivitas = view.findViewById(R.id.rv_aktivitas);
        rvAktivitas.setHasFixedSize(true);
        list.addAll(AktivitasData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        rvAktivitas.setLayoutManager(layoutManager);
        ListAktivitasAdapter listPlayerAdapter = new ListAktivitasAdapter(list);
        rvAktivitas.setAdapter(listPlayerAdapter);
    }
}
