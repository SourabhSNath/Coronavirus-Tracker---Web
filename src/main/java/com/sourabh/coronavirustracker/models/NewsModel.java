package com.sourabh.coronavirustracker.models;

public class NewsModel {
    private String newsHeadline;
    private String newsBody;
    private String newsMetaData;

    public NewsModel() {

    }

    public NewsModel(String newsHeadline, String newsBody, String newsMetaData) {
        this.newsHeadline = newsHeadline;
        this.newsBody = newsBody;
        this.newsMetaData = newsMetaData;
    }

    public String getNewsHeadline() {
        return newsHeadline;
    }

    public void setNewsHeadline(String newsHeadline) {
        this.newsHeadline = newsHeadline;
    }

    public String getNewsBody() {
        return newsBody;
    }

    public void setNewsBody(String newsBody) {
        this.newsBody = newsBody;
    }

    public String getNewsMetaData() {
        return newsMetaData;
    }

    public void setNewsMetaData(String newsMetaData) {
        this.newsMetaData = newsMetaData;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "newsHeadline='" + newsHeadline + '\'' +
                ", newsBody='" + newsBody + '\'' +
                ", newsMetaData='" + newsMetaData + '\'' +
                '}';
    }
}
