package com.example.mysport.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.mysport.R;
import com.example.mysport.adapter.NewsAdapter;
import com.example.mysport.data.NewsApi;
import com.example.mysport.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class KabarOlahraga extends Fragment implements NewsAdapter.onSelectData {

        RecyclerView rvSportNews;
    NewsAdapter newsAdapter;
    List<News> modelNews = new ArrayList<>();
    ProgressDialog progressDialog;

    public KabarOlahraga() {
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
        View view = inflater.inflate(R.layout.fragment_kabar_olahraga, container, false);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Mohon tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        rvSportNews = view.findViewById(R.id.rvNews);
        rvSportNews.setHasFixedSize(true);
        rvSportNews.setLayoutManager(new LinearLayoutManager(getActivity()));
//        setupToolbar();
        loadJSON();


        return view;
    }

    private void loadJSON() {
                progressDialog.show();
        AndroidNetworking.get(NewsApi.GET_CATEGORY_SPORTS)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray playerArray = response.getJSONArray("articles");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                News dataApi = new News();
                                dataApi.setTitle(temp.getString("title"));
                                dataApi.setUrl(temp.getString("url"));
                                dataApi.setPublisedAt(temp.getString("publishedAt"));
                                dataApi.setUrlToImage(temp.getString("urlToImage"));

                                modelNews.add(dataApi);
                                showNews();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                    }
                });

    }

    private void showNews() {
        newsAdapter = new NewsAdapter(getActivity(), modelNews, this);
        rvSportNews.setAdapter(newsAdapter);
    }

        @Override
    public void onSelected(News mdlNews) {
        DetailKabar detailAktivitas = new DetailKabar();
        Bundle bundle = new Bundle();
        bundle.putString("url", mdlNews.getUrl());
        detailAktivitas.setArguments(bundle);
        loadFragment(detailAktivitas);
//        startActivity(new Intent(getActivity(), OpenNewsActivity.class).putExtra("url", mdlNews.getUrl()));
    }

   private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

}
