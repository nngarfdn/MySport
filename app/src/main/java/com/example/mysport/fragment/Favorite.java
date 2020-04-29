package com.example.mysport.fragment;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysport.R;
import com.example.mysport.adapter.ListAktivitasAdapter;
import com.example.mysport.data.FavoriteDbHelper;
import com.example.mysport.model.Aktivitas;

import java.util.ArrayList;
import java.util.List;


public class Favorite extends Fragment {
    private EditText edtNama, edtBB, edtTB;
    private Button cekHasil ;


    public Favorite() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        edtNama = view.findViewById(R.id.edit_text_nama);
        edtBB = view.findViewById(R.id.edit_text_bb);
        edtTB = view.findViewById(R.id.edit_text_tb);
        cekHasil = view.findViewById(R.id.cekHasil);

        cekHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = edtNama.getText().toString().trim();
        String sBeratBadan = edtBB.getText().toString().trim();
        String sTinggiBadan = edtTB.getText().toString().trim();
        String hasil;
        String ket;



        if (edtNama.getText().toString().equals("") || edtBB.getText().toString().equals("") || edtTB.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(), "Mohon untuk melengkapi data", Toast.LENGTH_SHORT).show();
        } else {
            double beratBadan = Double.parseDouble(sBeratBadan);
            double tinggiBadan = Double.parseDouble(sTinggiBadan);
            //rumus
            //BMI = Berat Badan kg/ (Tinggi Badan m * Tinggi Badan m)
            double bmi = beratBadan / (tinggiBadan * tinggiBadan * 0.0001);
            Log.d("tag", "Nama = " + nama + "\nbmi = " + bmi + "");
            if (bmi < 18.5) {
                //Log.d("keterangan Perempuan", "Under Weight/Kurus – Sebaiknya mulai menambah berat badan dan mengkonsumsi makanan berkarbohidrat di imbangi dengan olah raga");
                hasil = "Under Weight/Kurus";
                ket = "Sebaiknya mulai menambah berat badan dan mengkonsumsi makanan berkarbohidrat di imbangi dengan olah raga";
            } else if (bmi >= 18.5 && bmi < 25) {
                //Log.d("keterangan Perempuan","Normal Weight/Normal – Bagus, berat badan anda termasuk kategori ideal");
                hasil = "Normal Weight/Normal";
                ket = "Bagus, berat badan anda termasuk kategori ideal";
            } else if (bmi >= 25 && bmi < 30) {
                //Log.d("keterangan Perempuan","Over Weight/Kegemukan – anda sudah masuk kategori gemuk. sebaiknya hindari makanan berlemak dan mulailah meningkatkan olahraga seminggu minimal 2 kali");
                hasil = "Over Weight/Kegemukan";
                ket = "Anda sudah masuk kategori gemuk. sebaiknya hindari makanan berlemak dan mulailah meningkatkan olahraga seminggu minimal 2 kali";
            } else {
                //Log.d("keterangan Perempuan","\tObesitas – Sebaiknya segera membuat program menurunkan berat badan karena anda termasuk kategori obesitas/ terlalu gemuk dan tidak baik bagi kesehatan");
                hasil = "Obesitas";
                ket = "Sebaiknya segera membuat program menurunkan berat badan karena anda termasuk kategori obesitas/ terlalu gemuk dan tidak baik bagi kesehatan";
            }
            Log.d("tag", "Nama = " + nama + "\nbmi = " + bmi + "\n" + "hasil : " + hasil + "\nket : " + ket + "\n");
        HasilFragment hasilFragment = new HasilFragment();
        Bundle bundle = new Bundle();
        bundle.putString("EXTRA_NAMA", nama);
        bundle.putDouble("EXTRA_BB", beratBadan);
        bundle.putDouble("EXTRA_TB", tinggiBadan);
        bundle.putDouble("EXTRA_BMI", bmi);
        bundle.putString("EXTRA_HASIL", hasil);
        bundle.putString("EXTRA_KET", ket);
        hasilFragment.setArguments(bundle);
        loadFragment(hasilFragment);
        }
            }
        });


        return view;
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
