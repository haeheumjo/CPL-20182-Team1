package com.example.juhyeon.waygo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.card.MaterialCardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

public class AddScheduleActivity extends AppCompatActivity {

    int selectedyear,selectedmonth,selectedday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        //CalendarView 인스턴스 만들기
        CalendarView calendar = (CalendarView)findViewById(R.id.calendar);
        //리스너 등록
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedyear=year;
                selectedmonth=month;
                selectedday=dayOfMonth;
            }
        });

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddScheduleActivity.this,SettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("selectedyear",selectedyear);
                intent.putExtra("selectedmonth",selectedmonth);
                intent.putExtra("selectedday",selectedday);
                startActivity(intent);
            }
        });
    }
}
