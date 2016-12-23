package com.phoenix.math;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

public class MainActivity extends AppCompatActivity {


    RelativeLayout container;
    LinearLayout control;
    LinearLayout tips;



    Button start;

    Animation hide_out, show_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hide_out = AnimationUtils.loadAnimation(this, R.anim.hide_out);
        show_in = AnimationUtils.loadAnimation(this, R.anim.show_in);
        container = (RelativeLayout) findViewById(R.id.container);
        control = (LinearLayout) findViewById(R.id.controll);
        start = (Button) findViewById(R.id.start);
        tips = (LinearLayout) findViewById(R.id.tips);
        tips.setVisibility(View.INVISIBLE);
        circle_animator.run();



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tips.startAnimation(hide_out);
                animate(control).setDuration(1000).rotationYBy(360).y(-100).alpha(0).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        finish();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
            }
        });





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tips.setVisibility(View.VISIBLE);
                tips.startAnimation(show_in);
            }
        }, 600);
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
