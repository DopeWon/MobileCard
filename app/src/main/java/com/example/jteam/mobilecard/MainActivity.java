package com.example.jteam.mobilecard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;

    private DatabaseReference idDatabaseReference, pwDatabaseReference;
    EditText IDInput, passwordInput;
    Button loginButton;
    String autoID, autoPW;
    CheckBox checkBox;
    boolean autoCK;
    boolean pwcheck = false;
    boolean idcheck = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backPressCloseHandler = new BackPressCloseHandler(this);
        idDatabaseReference = FirebaseDatabase.getInstance().getReference("userID");

        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoID = auto.getString("idinput", null);
        autoPW = auto.getString("pwinput", null);
        autoCK = auto.getBoolean("check", false);

        IDInput = (EditText)findViewById(R.id.IDInput);
        passwordInput = (EditText)findViewById(R.id.passwordInput);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        loginButton =(Button)findViewById(R.id.loginButton);
       if(autoID != null && autoPW != null)
        {
            if(autoCK)
            {
                Toast.makeText(getApplicationContext(), "로그인 성공!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }
        else {
           loginButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   idDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {


                           Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
                           while (child.hasNext()) {
                               idcheck = false;
                               pwcheck = false;
                               if (child.next().getKey().equals(IDInput.getText().toString())) {

                                   idcheck = true;
                                   pwDatabaseReference = FirebaseDatabase.getInstance().getReference("userID").child(IDInput.getText().toString()).child("password");
                                   pwDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                           String receivePW = (String) dataSnapshot.getValue();

                                           if (receivePW.equals(passwordInput.getText().toString())) {
                                               pwcheck = true;
                                               SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                                               SharedPreferences.Editor autoLogin = auto.edit();
                                               autoLogin.putString("idinput", IDInput.getText().toString());
                                               if(checkBox.isChecked()) {

                                                   autoLogin.putString("pwinput", passwordInput.getText().toString());
                                                   autoLogin.putBoolean("check", checkBox.isChecked());

                                               }
                                               autoLogin.commit();
                                               Toast.makeText(getApplicationContext(), "로그인 성공!", Toast.LENGTH_LONG).show();
                                               Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                               startActivity(intent);
                                               finish();
                                           } else {
                                               Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                                           }
                                       }

                                       @Override
                                       public void onCancelled(@NonNull DatabaseError databaseError) {

                                       }
                                   });


                                   if (idcheck == true && pwcheck == false) {

                                       break;
                                   }

                               }
                           }
                           if (idcheck == false)
                               Toast.makeText(getApplicationContext(), "존재하지 않는 아이디입니다.", Toast.LENGTH_LONG).show();
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });
               }
           });
       }
    }

    @Override public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }



}
