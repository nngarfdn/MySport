package com.example.mysport.model;

import android.graphics.Color;

import com.example.mysport.R;

import java.util.ArrayList;

public class AktivitasData {

    private static String[] aktivitasName = {
      "Push Up",
      "Sit Up",
      "Push Up",
      "Sit Up"
    };

    private static String[] aktivitasDetail = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mattis vitae a duis pretium nisl ut ac est cursus. Elit, venenatis donec id cursus turpis nulla. Tellus egestas ac elit vitae. Elementum nullam ullamcorper interdum sit ornare eu tempus sodales gravida. Amet diam, dis tempor, pellentesque etiam. Cras elementum rhoncus magna lectus vehicula vel hendrerit. Sit sed eget bibendum vestibulum, orci commodo volutpat arcu. Est convallis interdum dictumst elementum congue. Ornare.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mattis vitae a duis pretium nisl ut ac est cursus. Elit, venenatis donec id cursus turpis nulla. Tellus egestas ac elit vitae. Elementum nullam ullamcorper interdum sit ornare eu tempus sodales gravida. Amet diam, dis tempor, pellentesque etiam. Cras elementum rhoncus magna lectus vehicula vel hendrerit. Sit sed eget bibendum vestibulum, orci commodo volutpat arcu. Est convallis interdum dictumst elementum congue. Ornare.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mattis vitae a duis pretium nisl ut ac est cursus. Elit, venenatis donec id cursus turpis nulla. Tellus egestas ac elit vitae. Elementum nullam ullamcorper interdum sit ornare eu tempus sodales gravida. Amet diam, dis tempor, pellentesque etiam. Cras elementum rhoncus magna lectus vehicula vel hendrerit. Sit sed eget bibendum vestibulum, orci commodo volutpat arcu. Est convallis interdum dictumst elementum congue. Ornare.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mattis vitae a duis pretium nisl ut ac est cursus. Elit, venenatis donec id cursus turpis nulla. Tellus egestas ac elit vitae. Elementum nullam ullamcorper interdum sit ornare eu tempus sodales gravida. Amet diam, dis tempor, pellentesque etiam. Cras elementum rhoncus magna lectus vehicula vel hendrerit. Sit sed eget bibendum vestibulum, orci commodo volutpat arcu. Est convallis interdum dictumst elementum congue. Ornare."
    };

    private static String[] aktivitasPhoto = {
            "https://i.imgur.com/pPWmzSI.png",
            "https://i.imgur.com/dWlFLNN.png",
             "https://i.imgur.com/pPWmzSI.png",
            "https://i.imgur.com/dWlFLNN.png"

    };

    private static  int[] aktivitasColor = {
            Color.parseColor("#86E2F1"),
            Color.parseColor("#FFD082"),
            Color.parseColor("#FE7C7C"),
            Color.parseColor("#86E2F1")
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
