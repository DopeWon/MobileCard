package com.example.jteam.mobilecard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hideActionBar();

        ViewGroup book_click = (ViewGroup) findViewById(R.id.book_click);
        ViewGroup dvd_click = (ViewGroup) findViewById(R.id.dvd_click);
        ViewGroup home_click = (ViewGroup) findViewById(R.id.home_click);


        book_click.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), BookActivity.class);
                startActivity(intent);
            }
        });

        dvd_click.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), DvdActivity.class);
                startActivity(intent);
            }
        });
        home_click.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });



    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.hide();
        }
    }
}
