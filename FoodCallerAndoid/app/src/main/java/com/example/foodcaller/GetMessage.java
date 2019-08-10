package com.example.foodcaller;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class GetMessage extends AsyncTask <Void, Void, Void>
{
    private String LOG_TAG ="Log From GetMessage";

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(LOG_TAG, "This is the IP: " + getLocalIpAddress());
        Socket socket ;
        try {
            ServerSocket serverSocket = new ServerSocket(50001);
            Log.d(LOG_TAG, "Waiting for new Connection");
            socket = serverSocket.accept();
            //BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Log.d(LOG_TAG, "Got Connection");
            serverSocket.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e(LOG_TAG, ex.toString());
        }
        return null;
    }

}
