package com.example.hayeongbae.testclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ClientNet clientNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientNet = new ClientNet();
    }

    public void onClickButton(View view) {
        JSONObject data = new JSONObject();

        try {
            data.put("date","1996/02/14");
            data.put("time","");
            data.put("locationType",1);
            data.put("latitude",0.1);
            data.put("longitude",0.2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        clientNet.sendScheduleInfo(data);
    }
}
