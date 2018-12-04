package com.example.jteam.mobilecard;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        hideActionBar();
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.hide();
        }
    }
}
