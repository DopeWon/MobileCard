package com.example.jteam.mobilecard;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class InoutActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inout);
        hideActionBar();

        final ArrayList<String> items = new ArrayList<String>();
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) findViewById(R.id.main_lv_list);
        listview.setAdapter(adapter);

        Button addButton = (Button)findViewById(R.id.add);
        addButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v){
                int count;
                count = adapter.getCount();

                //아이템추가
                items.add("장소\t"+Integer.toString(count+1));

                //listview갱신
                adapter.notifyDataSetChanged();
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
