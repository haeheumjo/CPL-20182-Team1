package com.example.hayeongbae.testclient;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ManageConnectedSocket extends Thread {
    private final BluetoothSocket mmSocket;
    private InputStream mInputStream = null;
    private OutputStream mOutputStream = null;

    public ManageConnectedSocket(BluetoothSocket socket, Activity mActivity) {
        mmSocket = socket;
        try {
            mInputStream = mmSocket.getInputStream();
            mOutputStream = mmSocket.getOutputStream();
        } catch (IOException e) {
            Toast.makeText(mActivity, "연결할 장치를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void run() {
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
        while (true) {
            try {
                // Read from the InputStream
                bytes = mInputStream.read(buffer);
                // Send the obtained bytes to the UI activity
               // mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                //        .sendToTarget();
            } catch (IOException e) {
                break;
            }
        }
    }

    /* Call this from the main activity to send data to the remote device */
    public void write(byte[] bytes) {
        try {
            mOutputStream.write(bytes);
        } catch (IOException e) { }
    }

    /* Call this from the main activity to shutdown the connection */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
}
