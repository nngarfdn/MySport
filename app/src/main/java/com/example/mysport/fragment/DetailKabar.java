package com.example.mysport.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.mysport.R;

import java.util.Objects;


public class DetailKabar extends Fragment {

     private ProgressBar mProgressBar;

    String url = "https://www.google.com";


    public DetailKabar() {
        // Required empty public constructor
    }
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_kabar, container, false);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mProgressBar.setMax(100);

        Bundle bundle = getArguments();

        assert bundle != null;
        bundle.getString("url");

        url =  getArguments().getString("url");

        WebView mWebView = view.findViewById(R.id.read_news);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        mProgressBar.setProgress(0);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String newUrl) {
                view.loadUrl(newUrl);
                mProgressBar.setProgress(0);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String urlStart, Bitmap favicon) {
                //mProgressBar.setProgress(0);
                url = urlStart;
                Objects.requireNonNull(getActivity()).invalidateOptionsMenu();

            }

            @Override
            public void onPageFinished(WebView view, String urlPage) {
                mProgressBar.setVisibility(View.GONE);
                Objects.requireNonNull(getActivity()).invalidateOptionsMenu();
            }
        });
        return view;
    }
}
