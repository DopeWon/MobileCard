package com.example.jteam.mobilecard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;

public class HomeActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;
    String autoID;
    private DatabaseReference userDatabaseReference;
    TextView name, college, major, stunum;
    ImageView picture;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //hideActionBar();
        backPressCloseHandler = new BackPressCloseHandler(this);


        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoID = auto.getString("idinput", null);

        userDatabaseReference = FirebaseDatabase.getInstance().getReference("userID").child(autoID);
        userDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {


                name = (TextView)findViewById(R.id.name);
                name.setText((CharSequence) dataSnapshot.child("name").getValue());

                college = (TextView)findViewById(R.id.college);
                college.setText((CharSequence) dataSnapshot.child("college").getValue());

                major = (TextView)findViewById(R.id.major);
                major.setText((CharSequence) dataSnapshot.child("major").getValue());

                stunum = (TextView)findViewById(R.id.stunum);
                stunum.setText((CharSequence) dataSnapshot.child("stuNum").getValue());

                picture = (ImageView)findViewById(R.id.picture);
                Thread mThread = new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            URL url = new URL((String) dataSnapshot.child("imagepath").getValue());
                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                            conn.connect();

                            InputStream is = conn.getInputStream();
                            bitmap = BitmapFactory.decodeStream(is);

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                mThread.start();

                try
                {
                    mThread.join();
                    picture.setImageBitmap(bitmap);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ViewGroup book_click = (ViewGroup) findViewById(R.id.book_click);
        ViewGroup dvd_click = (ViewGroup) findViewById(R.id.dvd_click);
        ViewGroup dorm_click = (ViewGroup) findViewById(R.id.dorm_click);
        ViewGroup food_click = (ViewGroup) findViewById(R.id.food_click);


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
        food_click.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), ResCalendarActivity.class);
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
