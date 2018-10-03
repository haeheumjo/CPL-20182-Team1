package com.example.juhyeon.waygo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    int selectedyear,selectedmonth,selectedday,selectedplace;
    RadioButton place1,place2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Intent intent=getIntent();
        selectedyear=intent.getIntExtra("selectedyear",0);
        selectedmonth=intent.getIntExtra("selectedmonth",0);
        selectedday=intent.getIntExtra("selectedday",0);

        TextView text=(TextView) findViewById(R.id.date);
        text.setText(selectedyear+"년 "+selectedmonth+"월 "+selectedday+"일" );

        Button time = (Button) findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            TimePickerFragment timefragment=new TimePickerFragment();
            timefragment.show(getSupportFragmentManager(),TimePickerFragment.FRAGMENT_TAG);
            }
        });



        Button finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        place1 = (RadioButton) findViewById(R.id.place1);
        place2 = (RadioButton) findViewById(R.id.place2);

        place1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               selectedplace=1;
            }
        });

        place2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedplace=2;
            }
        });
    }
}
