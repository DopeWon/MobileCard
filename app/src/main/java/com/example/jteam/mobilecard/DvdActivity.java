package com.example.jteam.mobilecard;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DvdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dvd);
        hideActionBar();
    }
    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.hide();
        }
    }
}
