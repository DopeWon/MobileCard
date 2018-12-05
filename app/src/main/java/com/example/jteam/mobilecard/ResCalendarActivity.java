package com.example.jteam.mobilecard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResCalendarActivity extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("yyyy년 MM월", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eating_calendar);



        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("2018년 12월");

        compactCalendar = (CompactCalendarView) findViewById(R.id.eatingcalendarview);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        //이벤트 추가

        final Event ev1 = new Event(Color.BLACK, 1542931200000L, "Teachers' Professional Day");
        compactCalendar.addEvent(ev1);


        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                //
                if (dateClicked.toString().compareTo("Fri Nov 23 00:00:00 GMT+00:00 2018") == 0) {
                    Toast.makeText(context, "Teachers' Professional Day", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), RestaurantActivity.class);
                    startActivity(intent);
                }
                //이벤트 클릭 안했을 때
                else {
                    Toast.makeText(context, ""+dateClicked.toString(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
    }
}
