package com.example.jteam.mobilecard;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference idDatabaseReference, pwDatabaseReference;
    EditText IDInput, passwordInput;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideActionBar();

        idDatabaseReference = FirebaseDatabase.getInstance().getReference("userID");
        IDInput = (EditText)findViewById(R.id.IDInput);
        passwordInput = (EditText)findViewById(R.id.passwordInput);

        loginButton =(Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
                        while(child.hasNext())
                        {
                            if(child.next().getKey().equals(IDInput.getText().toString()))
                            {
                               // if(비밀번호도 일치하면)    괄호안에 들어갈 문법만 찾으면 완성
                              //  {
                                    Toast.makeText(getApplicationContext(), "로그인 성공!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                    return;
                              //  }
                               /* else
                                {
                                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                                    child = dataSnapshot.getChildren().iterator();
                                }*/
                            }
                        }
                        Toast.makeText(getApplicationContext(),"존재하지 않는 아이디입니다.",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
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
