package com.example.mysport.model;

import android.graphics.Color;

import com.example.mysport.R;

import java.util.ArrayList;

public class AktivitasData {

    private static String[] aktivitasName = {
      "Push Up",
      "Sit Up",
      "Pull Up",
      "Lunge"
    };

    private static String[] aktivitasDetail = {
            "Berfungsi untuk menguatkan otot bisep maupun trisep. Posisi awal tidur tengkurap dengan tangan di sisi kanan kiri badan. Kemudian badan didorong ke atas dengan kekuatan tangan. Posisi kaki dan badan tetap lurus atau tegap",
            "Latih ketahanan perut untuk memperkuat, mengencangkan dan mengencangkan otot-otot perut. Ini mirip dengan crunch, tetapi sit-up memiliki jangkauan gerak yang lebih lengkap dan kondisi otot tambahan.",
            "Latih kekuatan tubuh bagian atas. Angkat badan dilakukan dengan cara menggantungkan tubuh dengan tangan dan mengangkatnya",
            "Perkuat lutut kakimu dengan gerakan ini.\n" +
                    "\n" +
                    "Pisahkan salah satu kaki ke depan.\n" +
                    "Pastikan telapak kaki yang berada di depan rata dengan lantai.\n" +
                    "Tekuk lutut sampai lutut kaki yang berada di belakang menyentuh lantai.\n" +
                    "Dorong kaki yang berada di depan untuk kembali ke posisi semula."
    };

    private static String[] aktivitasPhoto = {
            "https://i.imgur.com/pPWmzSI.png",
            "https://i.imgur.com/dWlFLNN.png",
             "https://i.imgur.com/6PN0CJ7.png",
            "https://i.imgur.com/6S3ehTf.png"

    };

    private static  int[] aktivitasColor = {
            Color.parseColor("#86E2F1"),
            Color.parseColor("#FFD082"),
            Color.parseColor("#86E2F1"),
            Color.parseColor("#FE7C7C")
    };

    public static ArrayList<Aktivitas> getListData() {
        ArrayList<Aktivitas> list = new ArrayList<>();
        for (int position = 0; position< aktivitasName.length; position++){
            Aktivitas aktivitas = new Aktivitas();
            aktivitas.setName(aktivitasName[position]);
            aktivitas.setDetail(aktivitasDetail[position]);
            aktivitas.setPhoto(aktivitasPhoto[position]);
            aktivitas.setColor(aktivitasColor[position]);
            list.add(aktivitas);
        }
        return list;
    }
}
