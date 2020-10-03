package com.chandalala.boundservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyBoundService extends Service {

    private final IBinder iBinder = new MyLocalBinder();

    public MyBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        // TODO: Return the communication channel to the service.
        return iBinder;
    }

    // This particular service's job is to return current time
    public String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
        return simpleDateFormat.format(new Date());
    }

    public class MyLocalBinder extends Binder{
            MyBoundService getService(){
                return MyBoundService.this;
            }
    }
}
