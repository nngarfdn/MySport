package com.example.mysport.model;

public class News {
    private String title;
    private String publisedAt;
    private String url;
    private String urlToImage;

    public News() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisedAt() {
        return publisedAt;
    }

    public void setPublisedAt(String publisedAt) {
        this.publisedAt = publisedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
