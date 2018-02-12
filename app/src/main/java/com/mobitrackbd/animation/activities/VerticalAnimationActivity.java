package com.mobitrackbd.animation.activities;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Point;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mobitrackbd.animation.R;

public class VerticalAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mRootLayout;
    private FloatingActionButton mFab;

    private boolean isFabClick;

    private int whichAnimation;

    private Point[] vertices;

    private Button[] buttons;

    private int button_dim,button_spacing,buttonMaxWidth;

    int ANIMATION_DURATION = 300;

    /**
     * Coordination of button
     */
    int startPositionX = 0;
    int startPositionY = 0;

    int NUM_OF_BUTTON = 5;

    int[] enterDelay = {80, 120, 160, 40, 0};
    int[] exitDelay = {80, 40, 0, 120, 160};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_animation);

        mRootLayout = findViewById(R.id.root_layout);
        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(this);

        button_dim = (int) getResources().getDimension(R.dimen.button_dim);
        button_spacing = (int) getResources().getDimension(R.dimen.button_spacing);
        buttonMaxWidth = (int) getResources().getDimension(R.dimen.button_max_width);

        Log.d("HHHH",button_dim+"");
        Log.d("HHHH",button_spacing+"");


        calculateVertices(button_spacing,button_dim);
    }

    private void calculateVertices(int button_spacing, int button_dim) {
        vertices = new Point[NUM_OF_BUTTON];

        /**
         * Calculating the center of pentagon
         */
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int centerX = displaymetrics.widthPixels/2;
        int centerY = displaymetrics.heightPixels/2;

        int number =(int) NUM_OF_BUTTON/2;

        Log.d("HHHH",number+"");

        double firstPointY = centerY+(number-.5)*(button_dim+button_spacing);

        //Assing Vertices

        for (int i = 0; i < NUM_OF_BUTTON; i++){

            int y = (int)(firstPointY - i*(button_dim+button_spacing));
            vertices[i] = new Point(centerX-button_dim/2,y-button_dim/2);
        }

        buttons = new Button[NUM_OF_BUTTON];

        for (int i = 0; i < NUM_OF_BUTTON; i++) {
            //Adding button at (0,0) coordinates and setting their visibility to zero
            buttons[i] = new Button(VerticalAnimationActivity.this);
            buttons[i].setLayoutParams(new RelativeLayout.LayoutParams(5, 5));
            buttons[i].setX(0);
            buttons[i].setY(0);
            buttons[i].setTag(i);
            buttons[i].setOnClickListener(this);
            buttons[i].setVisibility(View.INVISIBLE);
            buttons[i].setBackgroundResource(R.drawable.circle_back);
            buttons[i].setTextColor(Color.WHITE);
            buttons[i].setText(String.valueOf(i + 1));
            buttons[i].setTextSize(20);
            /**
             * Adding those buttons in acitvities layout
             */
            mRootLayout.addView(buttons[i]);
        }


    }

    @Override
    public void onClick(View v) {

        boolean isFabClicked = false;

        switch (v.getId()) {
            case R.id.fab:
                isFabClicked = true;
                if (whichAnimation == 0) {
                    /**
                     * Getting the center point of floating action button
                     *  to set start point of buttons
                     */
                    //startPositionX = (int) v.getX() + 50;
                    startPositionX = (int) (v.getX()+mFab.getWidth()/2);
                    //startPositionY = (int) v.getY() + 50;
                    startPositionY = (int) (v.getY()+ mFab.getHeight()/2);


                    for (Button button : buttons) {
                        button.setX(startPositionX);
                        button.setY(startPositionY);
                        button.setVisibility(View.VISIBLE);
                    }

                    for (int i = 0; i < buttons.length; i++) {
                        playEnterAnimation(buttons[i], i);
                    }
                    whichAnimation = 1;
                } else {
                    for (int i = 0; i < buttons.length; i++) {
                        playExitAnimation(buttons[i], i);
                    }
                    whichAnimation = 0;
                }
        }


        if (!isFabClicked) {
            switch ((int) v.getTag()) {
                case 0:
                    Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(this, "Button 3 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(this, "Button 4 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(this, "Button 5 clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

    private void playEnterAnimation(final Button button, int position) {

       /* RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(button.getLayoutParams());
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        button.setLayoutParams(params);*/

        /**
         * Animator that animates buttons x and y position simultaneously with size
         */

        AnimatorSet myAnimator = new AnimatorSet();

        AnimatorSet buttonAnimator = new AnimatorSet();


        ValueAnimator buttonAnimatorX = ValueAnimator.ofFloat(startPositionX, vertices[position].x);
        buttonAnimatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Previous Code
                // button.setX((float) animation.getAnimatedValue() - button.getLayoutParams().width / 2);
                button.setX((float) animation.getAnimatedValue());
                button.requestLayout();
            }
        });
        buttonAnimatorX.setDuration(ANIMATION_DURATION);

        /**
         * ValueAnimator to update y position of a button
         */
        // Previous Code
       /* ValueAnimator buttonAnimatorY = ValueAnimator.ofFloat(startPositionY + 5,
                pentagonVertices[position].y);*/

        ValueAnimator buttonAnimatorY = ValueAnimator.ofFloat(startPositionY, vertices[position].y);
        buttonAnimatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                button.setY((float) animation.getAnimatedValue());
                button.requestLayout();
            }
        });
        buttonAnimatorY.setDuration(ANIMATION_DURATION);

        /**
         * This will increase the size of button
         */
        ValueAnimator buttonSizeAnimator = ValueAnimator.ofInt(5, button_dim);
        buttonSizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                button.getLayoutParams().width = (int) animation.getAnimatedValue();
                button.getLayoutParams().height = (int) animation.getAnimatedValue();
                button.requestLayout();
            }
        });
        buttonSizeAnimator.setDuration(ANIMATION_DURATION);

        ValueAnimator buttonAnimatorExpand = ValueAnimator.ofInt(button_dim, buttonMaxWidth);
        buttonAnimatorExpand.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               // button.getLayoutParams().width = (int) animation.getAnimatedValue();
                //button.getLayoutParams().height = (int) animation.getAnimatedValue();
                button.getLayoutParams().width = (int) animation.getAnimatedValue();
                //button.setTranslationX(-button.getWidth()/2);
                button.requestLayout();
            }
        });
        buttonAnimatorExpand.setDuration(ANIMATION_DURATION);


        /**
         * Add both x and y position update animation in
         *  animator set
         */
        buttonAnimator.play(buttonAnimatorX).with(buttonAnimatorY).with(buttonSizeAnimator);
        buttonAnimator.setStartDelay(enterDelay[position]);

       // buttonAnimator.start();

        myAnimator.play(buttonAnimator).before(buttonAnimatorExpand);
        myAnimator.start();
    }

    private void playExitAnimation(final Button button, int position) {

        /**
         * Animator that animates buttons x and y position simultaneously with size
         */
        AnimatorSet buttonAnimator = new AnimatorSet();

        /**
         * ValueAnimator to update x position of a button
         */

        // Previous Code
        /*ValueAnimator buttonAnimatorX = ValueAnimator.ofFloat(pentagonVertices[position].x - button.getLayoutParams().width / 2,
                startPositionX);*/

        ValueAnimator buttonAnimatorX = ValueAnimator.ofFloat(vertices[position].x, startPositionX);
        buttonAnimatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                button.setX((float) animation.getAnimatedValue());
                button.requestLayout();
            }
        });
        buttonAnimatorX.setDuration(ANIMATION_DURATION);


        ValueAnimator buttonAnimatorY = ValueAnimator.ofFloat(vertices[position].y, startPositionY);
        buttonAnimatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                button.setY((float) animation.getAnimatedValue());
                button.requestLayout();
            }
        });
        buttonAnimatorY.setDuration(ANIMATION_DURATION);

        /**
         * This will decrease the size of button
         */
        ValueAnimator buttonSizeAnimator = ValueAnimator.ofInt(button_dim, 5);
        buttonSizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                button.getLayoutParams().width = (int) animation.getAnimatedValue();
                button.getLayoutParams().height = (int) animation.getAnimatedValue();
                button.requestLayout();
            }
        });
        buttonSizeAnimator.setDuration(ANIMATION_DURATION);

        /**
         * Add both x and y position update animation in
         *  animator set
         */
        buttonAnimator.play(buttonAnimatorX).with(buttonAnimatorY).with(buttonSizeAnimator);
        buttonAnimator.setStartDelay(exitDelay[position]);
        buttonAnimator.start();
    }

}
