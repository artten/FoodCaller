package com.example.foodcaller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MessageSender extends AsyncTask <String, String, String>
{
    TextView text;
    Socket socket;
    DataOutputStream dos;
    PrintWriter printWriter;
    BufferedReader in;
    String Tag="Debug Mess ";
    String message;
    private int x =0 ;
    private int k =0;

    public MessageSender ( TextView rootView){
        this.text = rootView;
    }

    @Override
    protected String doInBackground(String... voids) {
        message = voids[0];
        final List<String> list = Checkavailibale();
        for( int i =0 ; i<list.size() ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        socket = new Socket(list.get(k), 50002);
                        Log.d(Tag, "ssssssssss: " + k);
                       // socket = new Socket();
                       // InetSocketAddress sockAdr = new InetSocketAddress(list.get(k), 50002);
                        //int timeout = 1000;
                        //socket.connect(sockAdr, timeout);

                        printWriter = new PrintWriter(socket.getOutputStream());
                        printWriter.write("blas");
                        printWriter.flush();
                        //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        Log.d(Tag, "HIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
                        //Log.d(Tag, in.readLine());
                        printWriter.close();
                        k++;

                    } catch (IOException e) {
                        //Log.d(Tag,"can not connect to host: " + host);
                        //e.printStackTrace();
                    }

                }
            }).start();

        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(Tag, "Starting");
        message = result;
        //Log.d(Tag, message);
        text.setText("Executed");
        Log.d(Tag, "Ending");
    }

    public List<String> Checkavailibale(){
        final List<String>  res = new ArrayList<String>();
        for(int i =1; i<255 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String host="192.168.1.";
                    String ending = String.valueOf(x);
                    host = host.concat(ending);

                    x++;


                    try {
                        //socket = new Socket(host, 50002);
                        socket = new Socket();
                        InetSocketAddress sockAdr = new InetSocketAddress(host, 50002);
                        int timeout = 100;
                        socket.connect(sockAdr, timeout);

                        res.add(host);
                    } catch (IOException e) {
                        //Log.d(Tag,"can not connect to host: " + host);
                        //e.printStackTrace();
                    }

                }
            }).start();

        }
        Log.d(Tag,"List" + res.toString());
        return res;
    }

}

