package com.example.jteam.mobilecard;

import android.content.Context;
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

public class CalendarActivity extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("yyyy년 MM월", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        //Set an event for Teachers' Professional Day 2016 which is 21st of October

        final Event ev1 = new Event(Color.BLACK, 1542931200000L, "Teachers' Professional Day");
        compactCalendar.addEvent(ev1);


        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
//if (dateClicked.toString().compareTo("Fri Nov 23 00:00:00 GMT 2018") == 0)
                if (dateClicked.toString().compareTo("") == 0) {
                    Toast.makeText(context, "Teachers' Professional Day", Toast.LENGTH_SHORT).show();
                }
                //이벤트 클릭 안했을 때
                else {
                    Toast.makeText(context, ""+dateClicked.getTime(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
    }
}
