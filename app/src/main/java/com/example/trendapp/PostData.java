package com.example.trendapp;

public class PostData {
    private int postWeight;
    private String url;
    public PostData(String url){
        postWeight = 0;
        this.url = url;
    }
    public void setPostWeight(int postWeight) {
        this.postWeight = postWeight;
    }

    public int getPostWeight() {
        return postWeight;
    }

    public String getUrl() {
        return url;
    }
}
