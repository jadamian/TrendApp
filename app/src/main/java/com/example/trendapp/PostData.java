package com.example.trendapp;

import java.io.Serializable;
import java.util.ArrayList;

public class PostData implements Serializable{
    private int postWeight;
    private String url;
    private int liked;
    private int shared;
    private ArrayList<String> keywords;
    public PostData(String url){
        postWeight = 0;
        this.url = url;
        liked = 0;
        shared = 0;
    }

    public PostData(String url, ArrayList<String> keys){
        this.url = url;
        keywords = keys;
    }

    public PostData(String url, int weight){
        this.url = url;
        this.postWeight = weight;
        liked = 0;
        shared = 0;
    }
    public void setPostWeight(int postWeight) {
        this.postWeight = postWeight;
    }

    public void engagement(){
        int eng = liked + shared;
    }

    public void liked(){
        if(liked <=0) liked = 1;

        //if(liked >= 0) liked = -3;
    }
    public void disliked(){
        //if(liked <=0) liked = 1;

        if(liked >= 0) liked = -3;
    }

    public void shared(){
        shared = 3;
    }

    public int getPostWeight() {
        return postWeight;
    }

    public String getUrl() {
        return url;
    }
}
