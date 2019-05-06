package com.example.trendapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //testing values
    private ArrayList<PostData> postList;
    public int currentPost = 0;
    private boolean liked;
    private boolean shared;
    private int changeweight;
    private UserData user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Write a message to the database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");
        Bundle b = getIntent().getExtras();
        user = new UserData(5);
        //userCreated = b.getBoolean("check");
        if(b != null){
            user = (UserData) b.getSerializable("userdata");
            PostGather posts = new PostGather(user, 60);
            postList = new ArrayList<PostData>(posts.getPosts());
        }
        else{
            PostGather p = new PostGather();
            postList = p.getPosts();
            user.setHomeCreated(postList);
        }
        //myRef.setValue("Hello, World!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //display link

        post = (WebView) findViewById(R.id.webView);
        post.setWebViewClient(new WebViewClient());
        post.loadUrl(postList.get(currentPost).getUrl());
        setButtons();
    }




    //buttons
    private Button next;
    private Button prev;
    private Button like;
    private Button disLike;
    private Button share;
    private Button feedSwitch;
    private WebView post;


    private void setButtons(){
        //next and prev functions
        next = (Button) findViewById(R.id.nextButton);
        prev = (Button) findViewById(R.id.prevButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPost++;
                if(currentPost >= postList.size()){
                    currentPost--;
                }
                displayLink();
                System.out.println("next");
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPost--;
                if(currentPost < 0){
                    currentPost = 0;
                }
                displayLink();
                System.out.println("prev");
            }
        });
        //Like and dislike funcions
        like = (Button) findViewById(R.id.like);
        disLike = (Button) findViewById(R.id.dislike);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //postList.get(currentPost).setPostWeight();
                postList.get(currentPost).liked();
                System.out.println("like: " + postList.get(currentPost).getPostWeight());
            }
        });
        disLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //postList.get(currentPost).setPostWeight(0);
                postList.get(currentPost).disliked();
                System.out.println("dislike: "+ postList.get(currentPost).getPostWeight());
            }
        });
        //sharing buttons
        share = (Button) findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                postList.get(currentPost).shared();
                System.out.println("share: "+ postList.get(currentPost).getUrl());
            }
        });

        //switch feeds button
        feedSwitch = (Button) findViewById(R.id.homeFeed);
        feedSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("userdata", user);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    public void displayLink(){
        //post.setWebViewClient(new WebViewClient());
        post.loadUrl(postList.get(currentPost).getUrl());
    }
}


