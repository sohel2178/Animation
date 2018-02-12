package com.mobitrackbd.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobitrackbd.animation.activities.FabPentagonActivity;
import com.mobitrackbd.animation.activities.ServiceTestActivity;
import com.mobitrackbd.animation.activities.TestActivity;
import com.mobitrackbd.animation.activities.VerticalAnimationActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnFabPentagon,btnStartServiceActivity,btnFabVertical,btnTestActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnFabPentagon = findViewById(R.id.fab_pentagon);
        btnFabVertical = findViewById(R.id.fab_vertical);
        btnStartServiceActivity = findViewById(R.id.start_service);
        btnTestActivity = findViewById(R.id.test_activity);
        btnFabPentagon.setOnClickListener(this);
        btnStartServiceActivity.setOnClickListener(this);
        btnFabVertical.setOnClickListener(this);
        btnTestActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fab_pentagon:
                startFabPentagonActivity();
                break;

            case R.id.fab_vertical:
                startFabVerticalActivity();
                break;

            case R.id.start_service:
                startServiceActivity();
                break;

            case R.id.test_activity:
                startTestActivity();
                break;
        }

    }

    private void startTestActivity() {
        startActivity(new Intent(getApplicationContext(), TestActivity.class));
    }

    private void startFabPentagonActivity() {
        startActivity(new Intent(getApplicationContext(), FabPentagonActivity.class));
    }

    private void startFabVerticalActivity() {
        startActivity(new Intent(getApplicationContext(), VerticalAnimationActivity.class));
    }

    private void startServiceActivity() {
        startActivity(new Intent(getApplicationContext(), ServiceTestActivity.class));
    }
}
