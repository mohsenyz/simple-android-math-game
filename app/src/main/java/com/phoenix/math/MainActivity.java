package com.phoenix.math;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

public class MainActivity extends AppCompatActivity {


    RelativeLayout container;
    LinearLayout control;
    LinearLayout tips;


    Animation hide_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hide_out = AnimationUtils.loadAnimation(this, R.anim.hide_out);
        container = (RelativeLayout) findViewById(R.id.container);
        control = (LinearLayout) findViewById(R.id.controll);
        tips = (LinearLayout) findViewById(R.id.tips);
        circle_animator.run();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tips.startAnimation(hide_out);
                animate(control).setDuration(1000).rotationYBy(360).y(-100).alpha(0);
            }
        }, 3000);
    }

    ValueAnimator colorAnimation;

    Runnable circle_animator = new Runnable() {
        @Override
        public void run() {
            int color2 = Color.parseColor("#57caa8");
            int color3 = Color.parseColor("#7141e2");
            int color4 = Color.parseColor("#c44e4e");
            int color5 = Color.parseColor("#c6b147");
            int color6 = Color.parseColor("#44c74b");
            int color7 = Color.parseColor("#d46cb3");
            colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), color2, color3, color4, color5, color6, color7);
            colorAnimation.setDuration(50000);
            colorAnimation.setRepeatMode(ValueAnimator.REVERSE);
            colorAnimation.setRepeatCount(ValueAnimator.INFINITE);
            colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    container.setBackgroundDrawable(new ColorDrawable((int) animator.getAnimatedValue()));
                }

            });
            colorAnimation.start();
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        if (!colorAnimation.isRunning())
            circle_animator.run();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (colorAnimation != null && colorAnimation.isRunning())
            colorAnimation.end();
    }
}
