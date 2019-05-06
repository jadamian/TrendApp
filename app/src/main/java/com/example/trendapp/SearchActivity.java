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

public class SearchActivity extends AppCompatActivity {

    private Button back;
    private Button next;
    private Button prev;
    private ArrayList<PostData> posts;
    private WebView link;
    private SearchView search;
    private int index;
    public String searchin;
    private UserData ud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        searchin = bundle.getString("searchquer");
        ud = (UserData) bundle.getSerializable("userdata");
        PostGather p = new PostGather(searchin);
        posts = new ArrayList<PostData>(p.getPosts());
        index = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        link = (WebView) findViewById(R.id.webView3);
        link.setWebViewClient(new WebViewClient());
        link.loadUrl(posts.get(index).getUrl());
        setButtons();
    }

    public void setButtons(){
        back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, HomeActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("userdata", ud);
                i.putExtras(b);

                startActivity(i);
            }
        });

        prev = (Button) findViewById(R.id.button4);
        next = (Button) findViewById(R.id.button5);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index--;
                if(index <= 0)
                    index = 0;
                show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if(index >= posts.size())
                    index = posts.size() - 1;
                show();
            }
        });

        search = (SearchView) findViewById(R.id.searchsearch);
        search.isSubmitButtonEnabled();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
                Bundle bund = new Bundle();
                bund.putString("searchquer", query);
                bund.putSerializable("userdata", ud);
                intent.putExtras(bund);

                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    public void show(){
        link.loadUrl(posts.get(index).getUrl());
    }
}
