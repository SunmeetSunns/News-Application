package com.example.kyachallahai;

import java.util.ArrayList;

public class NewsModal {
    private int totalResults;
    private String status;
    private ArrayList<Articles>articles;

    public NewsModal(ArrayList<Articles> articles,int totalResults,String status) {
        this.articles = articles;
        this.status=status;
        this.totalResults=totalResults;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
