package com.example.hayeongbae.testclient;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class ClientNet {
    static String dust=null;
    static String temperature=null;
    static String humidity=null;
    static int windowState = 3;

    private String ip = "192.168.123.110";
    private int port = 3000;
    public static com.github.nkzawa.socketio.client.Socket mSocket = null;

    public ClientNet(){
        try{
            setSoeckt(ip,port);
        }catch (Exception e){e.printStackTrace();}
        connect();
    }

    public void connect(){
        mSocket.connect();
        Log.d("test1","mSocket Connect");
    }

    public void setSoeckt(String ip, int port) throws IOException, URISyntaxException {
        try {
            mSocket = IO.socket("http://" + ip + ":" + port+"/");
            Log.d("test1", "mSocket Create" + " http://" + ip + ":" + port);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void sendScheduleInfo(JSONObject data){
        Log.i("test1", String.valueOf(data));
        mSocket.emit("sendScheduleInfo",data);
    }
}

