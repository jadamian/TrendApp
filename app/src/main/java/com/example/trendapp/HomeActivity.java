package com.example.trendapp;

import android.content.Intent;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class HomeActivity extends AppCompatActivity {

    //buttons
    private Button left;
    private Button prev;
    private Button like;
    private Button disLike;
    private Button share;
    private Button feedSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpButtons();
    }
    public void setUpButtons(){
        feedSwitch = (Button) findViewById(R.id.personalFeed);
        feedSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }
        });
    }
}
