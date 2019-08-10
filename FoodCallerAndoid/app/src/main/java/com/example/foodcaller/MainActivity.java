package com.example.foodcaller;

import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;


public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetMessage getMessage = new GetMessage();
                getMessage.execute();
            }
        });

    }

    public void SendText(View view) throws InterruptedException {
        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        if(wifiManager.isWifiEnabled()) {
            MessageSender messageSender = new MessageSender(textView);
            messageSender.execute(editText.getText().toString(),editText.getText().toString(), editText.getText().toString());
            Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
                Thread.sleep(500);
            }
        }
        else{
            wifiManager.setWifiEnabled(true);
            Thread.sleep(4000);
        }
    }

    @Override
    public void onDestroy() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetMessage getMessage = new GetMessage();
                getMessage.execute();
            }
        }).start();
        super.onDestroy();
    }
}



