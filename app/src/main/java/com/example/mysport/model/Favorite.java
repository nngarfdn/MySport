package com.example.mysport.model;

public class Favorite {
    private String key_id;
    private String name;
    private String detail;
    private String photo;
    private String favStatus;
    private int color;

    public Favorite() {
    }

    public Favorite(String key_id, String name, String detail, String photo, String favStatus, int color) {
        this.key_id = key_id;
        this.name = name;
        this.detail = detail;
        this.photo = photo;
        this.favStatus = favStatus;
        this.color = color;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
