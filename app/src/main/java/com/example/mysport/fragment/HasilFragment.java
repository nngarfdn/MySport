package com.example.mysport.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mysport.R;

public class HasilFragment extends Fragment {
    TextView txtNama, txtBb, txtTb, txtBmi, txtHasil, txtKet;
    String nama, hasil, ket;
    double bb, tb, bmi;
    double defaultValue = 0;
    public HasilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hasil, container, false);
        txtNama = view.findViewById(R.id.text_view_hasil_nama);
        txtBb=view.findViewById(R.id.text_view_hasil_bb);
        txtTb=view.findViewById(R.id.text_view_hasil_tb);
        txtBmi=view.findViewById(R.id.text_view_hasil_bmi);
        txtHasil=view.findViewById(R.id.text_view_hasil);
        txtKet=view.findViewById(R.id.text_view_hasil_ket);

        Bundle bundle = getArguments();
        assert bundle != null;
        nama = bundle.getString("EXTRA_NAMA");
        bb = bundle.getDouble("EXTRA_BB", defaultValue);
        tb = bundle.getDouble("EXTRA_TB", defaultValue);
        bmi = bundle.getDouble("EXTRA_BMI", defaultValue);
        hasil = bundle.getString("EXTRA_HASIL");
        ket = bundle.getString("EXTRA_KET");

        txtNama.setText(nama);
        txtBb.setText(" " + bb);
        txtTb.setText(" " + tb);
        txtBmi.setText("BMI : " + bmi);
        txtHasil.setText("Hasil : " + hasil);
        txtKet.setText("Keterangan : \n" + ket);
        return view;
    }
}
