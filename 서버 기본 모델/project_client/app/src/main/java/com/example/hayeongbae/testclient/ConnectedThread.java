package com.example.hayeongbae.testclient;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class ConnectedThread extends Thread {
    private BluetoothSocket mmSocket = null;
    private final BluetoothDevice mmDevice;
    UUID MY_UUID = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    public ConnectedThread(BluetoothDevice device, Activity mActivity) {
        mmDevice = device;

        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            mmSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
            mmSocket.connect();//기기 연결
        } catch (IOException e) {// Unable to connect; close the socket and get out
            try {
                Toast.makeText(mActivity, "기기를 연결할 수 없습니다.", Toast.LENGTH_LONG).show();
                mmSocket.close();
            } catch (IOException closeException) { }
            return;
        }

        // Do work to manage the connection (in a separate thread)
        ManageConnectedSocket manageConnectedSocket = new ManageConnectedSocket(mmSocket,mActivity);
    }
}