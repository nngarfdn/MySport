package com.example.mysport.adapter;

import java.util.Calendar;

public class TimeUnits {

public static String getTimeAgo(String timeStamp) {
        String timeAgo = "";

        String[] date = timeStamp.replace("T", "-")
                .replace("Z", "-")
                .replace(":", "-")
                .split("-");

        int year_a = Integer.parseInt(date[0]);
        int month_a = Integer.parseInt(date[1]);
        int day_a = Integer.parseInt(date[2]);
        int hour_a = Integer.parseInt(getHour(date[3]));
        int minute_a = Integer.parseInt(date[4]);
        //calendar a / time a (last time / published at )
        Calendar cal_a = Calendar.getInstance();
        cal_a.set(year_a, month_a, day_a, hour_a, minute_a);

        //calendar b / time b (time now)
        Calendar cal_b = Calendar.getInstance();
        int year_b = cal_b.get(Calendar.YEAR);
        int month_b = cal_b.get(Calendar.MONTH) + 1;
        int day_b = cal_b.get(Calendar.DAY_OF_MONTH);
        int hour_b = cal_b.get(Calendar.HOUR_OF_DAY);
        int minute_b = cal_b.get(Calendar.MINUTE);
        cal_b.set(year_b, month_b, day_b, hour_b, minute_b);

        final long MILIDETIK_SATU_MENIT = 60 * 1000;

        long wA = cal_a.getTimeInMillis();
        long wB = cal_b.getTimeInMillis();
        //selisih
        long selisih = wB - wA;
        int selisih_menit = (int) (selisih / MILIDETIK_SATU_MENIT);
        if (selisih_menit > 0) {
            if (selisih_menit > 60 && selisih_menit < 1440) {
                int jam = selisih_menit / 60;
                timeAgo = jam + " jam yang lalu";
            } else if (selisih_menit > 1440) {
                int hari = selisih_menit / 1440;
                timeAgo = hari + " hari yang lalu";
            } else {
                timeAgo = selisih_menit + " menit yang lalu";
            }
        }
        return timeAgo;
    }

    public static String getHour(String hour) {
        String result = "";
        switch (hour) {
            case "00":
                result = "07";
                break;
            case "01":
                result = "08";
                break;
            case "02":
                result = "09";
                break;
            case "03":
                result = "10";
                break;
            case "04":
                result = "11";
                break;
            case "05":
                result = "12";
                break;
            case "06":
                result = "13";
                break;
            case "07":
                result = "14";
                break;
            case "08":
                result = "15";
                break;
            case "09":
                result = "16";
                break;
            case "10":
                result = "17";
                break;
            case "11":
                result = "18";
                break;
            case "12":
                result = "19";
                break;
            case "13":
                result = "20";
                break;
            case "14":
                result = "21";
                break;
            case "15":
                result = "22";
                break;
            case "16":
                result = "23";
                break;
            case "17":
                result = "00";
                break;
            case "18":
                result = "01";
                break;
            case "19":
                result = "02";
                break;
            case "20":
                result = "03";
                break;
            case "21":
                result = "04";
                break;
            case "22":
                result = "05";
                break;
            case "23":
                result = "06";
                break;
        }
        return result;
    }
}

