package com.example.trendapp;

import android.os.AsyncTask;

import java.util.ArrayList;

//import FeedMaker.MLFeed;
//import FeedMaker.UserData;

public class PostGather //extends AsyncTask<UserData, Integer, ArrayList<String>>
{
    ArrayList<PostData> postUrl;
    public PostGather(){
        postUrl = new ArrayList<>();
        MLFeed home = new MLFeed();
        for(Posts p: home.getPosts()){
            postUrl.add(new PostData(p.getUrl()));
        }

    }

    public PostGather(int amount){
        UserData data = new UserData();
        postUrl = new ArrayList<PostData>();
        data.addInterest("cat");
        data.addInterest("tesla");
        data.addInterest("gordan ramsey");
        MLFeed feed = new MLFeed(data, amount);
        for(Posts p: feed.getPosts()){
            postUrl.add(new PostData(p.getUrl()));
        }
    }

    public PostGather(UserData ud, int amount){
        MLFeed feed = new MLFeed(ud, amount);
        postUrl = new ArrayList<PostData>();
        for(Posts p: feed.getPosts()){
            postUrl.add(new PostData(p.getUrl(), p.getKeywords()));
        }
    }

    public PostGather(String search){
        UserData data = new UserData();
        postUrl = new ArrayList<PostData>();
        MLFeed feed = new MLFeed(search);
        for(Posts p: feed.getPosts()){
            postUrl.add(new PostData(p.getUrl()));
        }
    }

    //@Override
    protected void doInBackground(UserData user){
        MLFeed feed = new MLFeed(user);
    }

    //@Override
    protected void onProgressUpdate(){
        int count = postUrl.size();

    }

    //@Override


    public ArrayList<PostData> getPosts(){
        return postUrl;
    }
}
