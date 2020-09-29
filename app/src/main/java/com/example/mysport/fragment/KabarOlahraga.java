package com.example.mysport.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mysport.R;
import com.example.mysport.adapter.NewsAdapter;
import com.example.mysport.model.ArticlesItem;
import com.example.mysport.viewmodel.SportViewModel;

import java.util.Objects;


public class KabarOlahraga extends Fragment implements NewsAdapter.onSelectData {

    RecyclerView rvSportNews;
    NewsAdapter newsAdapter;
    ProgressDialog progressDialog;
    SportViewModel sportViewModel;

    public KabarOlahraga() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sportViewModel = ViewModelProviders.of(this).get(SportViewModel.class);
        sportViewModel.loadResults();
        sportViewModel.getResults().observe(this, result -> {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rvSportNews.setLayoutManager(layoutManager);
            newsAdapter = new NewsAdapter(getActivity(), result.getArticles(), this);
            rvSportNews.setAdapter(newsAdapter);
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kabar_olahraga, container, false);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Mohon tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        rvSportNews = view.findViewById(R.id.rvNews);
        rvSportNews.setHasFixedSize(true);
        rvSportNews.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }



    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onSelected(ArticlesItem mdlNews) {
        DetailKabar detailAktivitas = new DetailKabar();
        Bundle bundle = new Bundle();
        bundle.putString("url", mdlNews.getUrl());
        detailAktivitas.setArguments(bundle);
        loadFragment(detailAktivitas);
    }

}
