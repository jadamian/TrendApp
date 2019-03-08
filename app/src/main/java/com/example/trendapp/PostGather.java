package com.example.trendapp;

import java.util.ArrayList;

public class PostGather {
    ArrayList<PostData> postUrl;
    public PostGather(){
        postUrl = new ArrayList<>();
        postUrl.add(new PostData("https://twitter.com/SpaceX/status/1102194489500753921?s=20"));
        postUrl.add(new PostData("https://twitter.com/GordonRamsay/status/1102194439844442113?s=20"));
        postUrl.add(new PostData("https://twitter.com/ItsMeowIRL/status/1102344556975136769?s=20"));
    }

    public ArrayList<PostData> getPosts(){
        return postUrl;
    }
}
