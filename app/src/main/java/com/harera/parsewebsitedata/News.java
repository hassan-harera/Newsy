package com.harera.parsewebsitedata;

public class News {

    private String imgUrl, title, newsUrl;

    public News(String imgUrl, String title, String newsUrl) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.newsUrl = newsUrl;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
