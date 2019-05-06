package com.example.trendapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.SearchView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    //buttons
    private Button next;
    private Button prev;
    private Button like;
    private Button disLike;
    private Button share;
    private Button feedSwitch;
    private SearchView searchBar;
    private String queryTxt;
    private ArrayList<PostData> posts;
    private WebView wb;
    private UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bund = getIntent().getExtras();
        user = (UserData) bund.getSerializable("userdata");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        index = 0;
        posts = new ArrayList<>();
        if(!user.getHomeCreated()){
            PostGather p = new PostGather();
            posts = p.getPosts();
            user.setHomeCreated(posts);
        }
        else{
            posts = user.getHome();
        }
        //posts.add(new PostData("https://i.redd.it/cb0uai6ds5xz.jpg"));
        wb = (WebView) findViewById(R.id.webView2);
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl(posts.get(index).getUrl());
        setUpButtons();
    }

    private int index;

    public void setUpButtons(){
        //next and prev
        next = (Button) findViewById(R.id.homeNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if(index >= posts.size()) index = posts.size() - 1;
                display();
            }
        });
        prev = (Button) findViewById(R.id.homePrev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index--;
                if(index <= 0)
                    index =0;
                display();
            }
        });

        //interaction buttons
        like = (Button) findViewById(R.id.homeLike);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        disLike = (Button) findViewById(R.id.homeDis);
        disLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        share = (Button) findViewById(R.id.homeShare);
        disLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        //to personal
        feedSwitch = (Button) findViewById(R.id.personalFeed);
        feedSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putBoolean("check", true);
                b.putSerializable("userdata", user);
                i.putExtras(b);

                startActivity(i);
            }
        });

        searchBar = (SearchView) findViewById(R.id.searchbar);
        searchBar.isSubmitButtonEnabled();

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //System.out.println("------------------------------------------" + queryTxt);
                Intent i = new Intent(HomeActivity.this, SearchActivity.class);
                Bundle b = new Bundle();
                b.putString("searchquer", query);
                b.putSerializable("userdata", user);

                i.putExtras(b);

                startActivity(i);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                queryTxt = newText;
                return false;
            }
        });
/*
        searchBar.setOnSearchClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //String str = textBar.getEditableText().toString();
                System.out.println("------------------------------------------" + str);
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            }
        });
        */
    }

    private void display(){
        wb.loadUrl(posts.get(index).getUrl());
    }
}
