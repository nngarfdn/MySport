package com.example.mysport.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mysport.R;
import com.example.mysport.data.FavoriteDbHelper;
import com.example.mysport.model.Aktivitas;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;

import java.util.Objects;



public class DetailAktivitas extends Fragment {

    private ImageView photo, back, imgFavorite;
    private Button doit;
    private TextView name, deskripsi;
    private RelativeLayout detail_layout;
    private FavoriteDbHelper favoriteDbHelper;
    private MaterialFavoriteButton materialFavoriteButtonNice;

    public DetailAktivitas() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_aktivitas, container, false);
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        imgFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                favoriteDbHelper = new FavoriteDbHelper(getContext());
//                aktivitas = new Aktivitas();
//                Bundle mBundle = getArguments();
//
//                assert mBundle != null;
//                final String nameDet = mBundle.getString("name");
//                final String photoDet = mBundle.getString("photo");
//                final String desDet = mBundle.getString("desc");
//                final int colorDet = mBundle.getInt("color");
//                final String status = mBundle.getString("fav_status");
//                final String key_id = mBundle.getString("key_id");
//
//                if (sta.equals("0")) {
//                    aktivitas.setFavStatus();
//                }
//            }
//        });
//        materialFavoriteButtonNice.setOnFavoriteChangeListener(
//                new MaterialFavoriteButton.OnFavoriteChangeListener() {
//                    Bundle mBundle = getArguments();
//                    final String status = mBundle.getString("fav_status");
//
//                    @Override
//                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
//                        if (favorite) {
//                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("com.delaroystudios.movieapp.DetailActivity", MODE_PRIVATE).edit();
//                            editor.putBoolean("Favorite Added", false);
//                            editor.commit();
//                            buttonView.setFavorite(favorite);
//                            saveFavorite();
//                            Snackbar.make(buttonView, "Added to Favorite",
//                                    Snackbar.LENGTH_SHORT).show();
//                        } else {
//                            final String key_id = mBundle.getString("key_id");
//                            favoriteDbHelper = new FavoriteDbHelper(getActivity());
//                            favoriteDbHelper.deleteFavorite(key_id);
//
//                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("com.delaroystudios.movieapp.DetailActivity", MODE_PRIVATE).edit();
//                            editor.putBoolean("Favorite Removed", true);
//                            editor.commit();
//                            Snackbar.make(buttonView, "Removed from Favorite",
//                                    Snackbar.LENGTH_SHORT).show();
//                        }
//
//                    }
//                }
//        );
        initView(view);

        getData();
        setOnClicKListeners();
        return view;
    }

    public void saveFavorite() {
        favoriteDbHelper = new FavoriteDbHelper(getContext());
        Aktivitas aktivitas = new Aktivitas();
        Bundle mBundle = getArguments();

        assert mBundle != null;
        final String nameDet = mBundle.getString("name");
        final String photoDet = mBundle.getString("photo");
        final String desDet = mBundle.getString("desc");
        final int colorDet = mBundle.getInt("color");
        final String key_id = mBundle.getString("key_id");
        aktivitas.setKey_id(key_id);
        aktivitas.setName(nameDet);
        aktivitas.setDetail(desDet);
        aktivitas.setPhoto(photoDet);
        aktivitas.setColor(colorDet);
        favoriteDbHelper.addFavorite(aktivitas);
    }


    private void setOnClicKListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home home = new Home();
                loadFragment(home);
            }
        });

        doit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 TickerTimer tickerTimer = new TickerTimer();
                //mengirim data
                Aktivitas aktivitas = new Aktivitas();
                Bundle mBundle = new Bundle();
                mBundle.putString("name", aktivitas.getName());
                mBundle.putString("photo", aktivitas.getPhoto());
                mBundle.putInt("color", aktivitas.getColor());

                tickerTimer.setArguments(mBundle);
            //    pindah fragment sama seperti intent
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, tickerTimer)
                        .addToBackStack(null).commit();
//                loadFragment(tickerTimer);
            }
        });

//        Bundle mBundle = getArguments();
//        final String status = mBundle.getString("fav_status");
//        final String key_id = mBundle.getString("key_id");

    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void getData() {
        Bundle mBundle = getArguments();

        assert mBundle != null;
        final String nameDet = mBundle.getString("name");
        final String photoDet = mBundle.getString("photo");
        final String desDet = mBundle.getString("desc");
        final int colorDet = mBundle.getInt("color");
//        final String status = mBundle.getString("fav_status");
//        final String key_id = mBundle.getString("key_id");
        name.setText(nameDet);
        deskripsi.setText(desDet);

        detail_layout.setBackgroundColor(colorDet);

        Picasso.get()
                .load(photoDet)
                .fit()
                .error(R.drawable.ic_launcher_background)
                .into(photo);


    }

    private void initView(View view) {
        photo = view.findViewById(R.id.photoDetail);
        name = view.findViewById(R.id.txtNameDetail);
        deskripsi = view.findViewById(R.id.descDetail);
        detail_layout = view.findViewById(R.id.detail_layout);
        back = view.findViewById(R.id.back);
        doit = view.findViewById(R.id.doit);
//        imgFavorite = view.findViewById(R.id.imgFavorite);
//        materialFavoriteButtonNice = (MaterialFavoriteButton) view.findViewById(R.id.favorite_button);
    }
}
