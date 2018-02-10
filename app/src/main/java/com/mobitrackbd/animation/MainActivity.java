package com.mobitrackbd.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobitrackbd.animation.activities.FabPentagonActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnFabPentagon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnFabPentagon = findViewById(R.id.fab_pentagon);
        btnFabPentagon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fab_pentagon:
                startFabPentagonActivity();
                break;
        }

    }

    private void startFabPentagonActivity() {
        startActivity(new Intent(getApplicationContext(), FabPentagonActivity.class));
    }
}
