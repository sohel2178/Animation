package com.mobitrackbd.animation.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mobitrackbd.animation.R;
import com.mobitrackbd.animation.Service.MyService;

public class ServiceTestActivity extends AppCompatActivity {

    private TextView textView;

    private Intent serviceIntent;

    private ServiceConnection mServiceConnection;
    private boolean isBound;
    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        textView = findViewById(R.id.textView);

        serviceIntent = new Intent(getApplicationContext(), MyService.class);

        startService(serviceIntent);

    }

    @Override
    protected void onStart() {
        bindService();
        super.onStart();
    }

    @Override
    protected void onStop() {
        unBindService();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        stopService(serviceIntent);
        super.onDestroy();
    }

    public void startService(View view) {
        startService(serviceIntent);
    }

    public void stopService(View view) {
        stopService(serviceIntent);
    }

    public void bindService(View view) {
        bindService();
    }

    private void bindService(){
        if(mServiceConnection==null){
            mServiceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    isBound = true; // When service is Connected

                    MyService.MyServiceBinder binder = (MyService.MyServiceBinder) iBinder;

                    myService = binder.getService();

                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    isBound = false; // When Service is not Connected

                }
            };

            bindService(serviceIntent,mServiceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    public void unBindService(View view) {

    }

    private void unBindService(){
        if(isBound){
            unbindService(mServiceConnection);
            isBound = false;
        }
    }

    public void getRandomNumber(View view) {
        textView.setText(String.valueOf(myService.getRandomNumber()));
    }
}
