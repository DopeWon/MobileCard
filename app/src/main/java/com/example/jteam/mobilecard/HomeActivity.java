package com.example.jteam.mobilecard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //hideActionBar();
        backPressCloseHandler = new BackPressCloseHandler(this);

        ViewGroup book_click = (ViewGroup) findViewById(R.id.book_click);
        ViewGroup dvd_click = (ViewGroup) findViewById(R.id.dvd_click);
        ViewGroup dorm_click = (ViewGroup) findViewById(R.id.dorm_click);


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
        dorm_click.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.logout) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = auto.edit();
            //editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지웁니다.
            editor.clear();
            editor.commit();
            Toast.makeText(getApplicationContext(), "로그아웃.", Toast.LENGTH_SHORT).show();
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }


   /* private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.hide();
        }
    }*/
}
