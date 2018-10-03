package com.example.hayeongbae.testclient;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.hayeongbae.testclient.BluetoothModule.REQUEST_ENABLE_BT;

public class MainActivity extends AppCompatActivity {

    private ClientNet clientNet;
    private BluetoothModule bluetoothModule = null;
    public static BluetoothAdapter mBluetoothAdapter ;

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

    public void onClickBlue(View view){
        if(bluetoothModule==null){
            bluetoothModule = new BluetoothModule(this);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // startActivityForResult 를 여러번 사용할 땐 이런 식으로 switch 문을 사용하여 어떤 요청인지 구분하여 사용함.
        switch(requestCode) {
            case REQUEST_ENABLE_BT:
                if(resultCode == RESULT_OK) { // 블루투스 활성화 상태
                    bluetoothModule.selectDevice();
                }
                else if(resultCode == RESULT_CANCELED) { // 블루투스 비활성화 상태 (종료)
                    Toast.makeText(getApplicationContext(), "블루투수를 사용할 수 없어 프로그램을 종료합니다", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
