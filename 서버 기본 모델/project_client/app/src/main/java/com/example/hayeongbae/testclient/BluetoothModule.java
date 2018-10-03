package com.example.hayeongbae.testclient;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Handler;

public class BluetoothModule {
    static final int REQUEST_ENABLE_BT = 2;
    private BluetoothAdapter mBluetoothAdapter ;
    private BluetoothDevice mRemoteDevice;

    private Activity mActivity;
    private Handler mHandler;
    Set<BluetoothDevice> pairedDevices;


    public BluetoothModule(Activity activity){

        mActivity = activity;

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {//Device doesn't support Bluetooth
            // Device does not support Bluetooth
            Toast.makeText(mActivity, "기기가 블루투스를 지원하지 않습니다.", Toast.LENGTH_LONG).show();
        }
        else
            activeBluetooth(activity);

    }

    public void activeBluetooth(Activity activity){
        if (!mBluetoothAdapter.isEnabled()) {//If the bluetooth state is off, turn on the state.
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent,REQUEST_ENABLE_BT);
        }

        selectDevice();

        MainActivity.mBluetoothAdapter = mBluetoothAdapter;
    }

    // 블루투스 지원하며 활성 상태인 경우.
    void selectDevice() {

        pairedDevices = mBluetoothAdapter.getBondedDevices();//페어링 된 기기 목록을 얻어오는 함수.
        final List<String> pairedDevicesArrayList = new ArrayList<String>();
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        if (pairedDevices.size() > 0) {//페어링된 장치가 있을 경우

            builder.setTitle("블루투스 장치 선택");

            for (BluetoothDevice device : pairedDevices) {// Loop through paired devices
                pairedDevicesArrayList.add(device.getName());
            }
            pairedDevicesArrayList.add("취소");

            final CharSequence[] items = pairedDevicesArrayList.toArray(new CharSequence[pairedDevicesArrayList.size()]);
            pairedDevicesArrayList.toArray(new CharSequence[pairedDevicesArrayList.size()]);

            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (item == pairedDevices.size()) { // 연결할 장치를 선택하지 않고 '취소' 를 누른 경우.
                        Toast.makeText(mActivity, "연결할 장치를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
                    } else { // 연결할 장치를 선택한 경우, 선택한 장치와 연결을 시도함.
                        BluetoothDevice selectedDevice=null;
                        selectedDevice = getDeviceFromBondList(items[item].toString());
                        ConnectedThread connectedThread = new ConnectedThread(selectedDevice,mActivity);
                    }
                }
            });
            builder.setCancelable(false);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else {
            Toast.makeText(mActivity, "페어링 된 장치가 없습니다.", Toast.LENGTH_LONG).show();
        }
    }

    BluetoothDevice getDeviceFromBondList(String deviceName) {
        BluetoothDevice selectedDevice = null;

        for(BluetoothDevice deivce : pairedDevices) {
            if(deviceName.equals(deivce.getName())) {
                selectedDevice = deivce;
                break;
            }
        }
        return selectedDevice;
    }

}
