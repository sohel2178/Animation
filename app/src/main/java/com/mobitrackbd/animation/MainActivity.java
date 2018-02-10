package com.mobitrackbd.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobitrackbd.animation.activities.FabPentagonActivity;
import com.mobitrackbd.animation.activities.ServiceTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnFabPentagon,btnStartServiceActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnFabPentagon = findViewById(R.id.fab_pentagon);
        btnStartServiceActivity = findViewById(R.id.start_service);
        btnFabPentagon.setOnClickListener(this);
        btnStartServiceActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fab_pentagon:
                startFabPentagonActivity();
                break;

            case R.id.start_service:
                startServiceActivity();
                break;
        }

    }

    private void startFabPentagonActivity() {
        startActivity(new Intent(getApplicationContext(), FabPentagonActivity.class));
    }

    private void startServiceActivity() {
        startActivity(new Intent(getApplicationContext(), ServiceTestActivity.class));
    }
}
