package com.mobitrackbd.animation.activities;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mobitrackbd.animation.R;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTarget,btnStart;

    private int buttonDim,maxDim;

    private boolean buttonClick;

    private int animState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        buttonDim = (int) getResources().getDimension(R.dimen.button_dim);
        maxDim = (int) getResources().getDimension(R.dimen.button_max_width);

        btnStart = findViewById(R.id.start);
        btnTarget = findViewById(R.id.target);

        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Log.d("HHHH",btnTarget.getX()+"");
        Log.d("HHHH",btnTarget.getPivotX()+"");
        Log.d("HHHH",btnTarget.getWidth()+"");

        buttonClick = !buttonClick;
        if (animState == 0) {

            AnimatorSet set = new AnimatorSet();


            ValueAnimator buttonSizeAnimator = ValueAnimator.ofInt(buttonDim, maxDim);
            buttonSizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    btnTarget.getLayoutParams().width = (int) animation.getAnimatedValue();
                    //button.getLayoutParams().height = (int) animation.getAnimatedValue();
                    btnTarget.requestLayout();
                }
            });
            buttonSizeAnimator.setDuration(300);

            buttonSizeAnimator.start();



        }

    }
}
