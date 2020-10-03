package com.chandalala.boundservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyBoundService myBoundService;
    boolean isBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService(new Intent(this, MyBoundService.class)
                ,serviceConnection, Context.BIND_AUTO_CREATE);


    }

    public void whatTime(View view) {
        String currentTime = myBoundService.getCurrentTime();
        TextView textView = findViewById(R.id.textview);
        textView.setText(currentTime);
    }

    /*
        Whenever you want to bind to a service, you need a service connection class
        This class dictates what you want to happen when you connect or disconnect from a service
    */

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.MyLocalBinder localBinder = (MyBoundService.MyLocalBinder) service;
            myBoundService = localBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}
