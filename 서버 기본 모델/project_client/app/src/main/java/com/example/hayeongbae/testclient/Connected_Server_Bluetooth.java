package com.example.hayeongbae.testclient;


import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

import static com.example.hayeongbae.testclient.MainActivity.mBluetoothAdapter;

public class Connected_Server_Bluetooth extends Thread {
    UUID MY_UUID = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    private final BluetoothServerSocket mmServerSocket;

    public Connected_Server_Bluetooth() {

        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code
           tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("Bluetooth_WAYgo", MY_UUID);
        } catch (IOException e) { }
        mmServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                break;
            }
            // If a connection was accepted
            if (socket != null) {
                // Do work to manage the connection (in a separate thread)
                //              manageConnectedSocket(socket);
                try {
                    mmServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /** Will cancel the listening socket, and cause the thread to finish */
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) { }
    }
}
