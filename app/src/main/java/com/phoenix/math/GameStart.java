package com.phoenix.math;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import com.phoenix.math.helper.NumberFormatting;
import com.phoenix.math.helper.Question;
import com.phoenix.math.helper.QuestionHelper;
import com.phoenix.math.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;
public class GameStart extends AppCompatActivity {
    int color2 = Color.parseColor("#57caa8");
    int color3 = Color.parseColor("#7141e2");
    int color4 = Color.parseColor("#c44e4e");
    int color5 = Color.parseColor("#c6b147");
    int color6 = Color.parseColor("#44c74b");
    int color7 = Color.parseColor("#d46cb3");
    int colors[] = {color2, color3, color4, color5, color6, color7};
    RelativeLayout container;
    LinearLayout score_container;
    TextView score_text, question, fake1, fake2, fake3, fake4;
    ProgressBar progressBar;
    private int TIME = 4500;
    private int IN_QUESTION = 0;
    private int SCORE = 0;
    Question current;
    Button exit;
    private List<Question> questionList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        container = (RelativeLayout) findViewById(R.id.container);
        score_container = (LinearLayout) findViewById(R.id.tips);
        score_text = (TextView) findViewById(R.id.score_txt);
        question = (TextView) findViewById(R.id.question);
        fake1 = (TextView) findViewById(R.id.fake1);
        fake2 = (TextView) findViewById(R.id.fake2);
        fake3 = (TextView) findViewById(R.id.fake3);
        fake4 = (TextView) findViewById(R.id.fake4);
        fake4 = (TextView) findViewById(R.id.fake4);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        circle_animator.run();
        initQuestions();
        score_text.setText(NumberFormatting.englishToArabic("" + SCORE));
        current = questionList.get(IN_QUESTION);
        question.setText(NumberFormatting.englishToArabic(current.getStatement() + " = ?"));
        List<Integer> items = current.getAllOf();
        fake1.setText(NumberFormatting.englishToArabic("" + items.get(0)));
        fake2.setText(NumberFormatting.englishToArabic("" + items.get(1)));
        fake3.setText(NumberFormatting.englishToArabic("" + items.get(2)));
        fake4.setText(NumberFormatting.englishToArabic("" + items.get(3)));
        fake1.setOnClickListener(onClickListener);
        fake2.setOnClickListener(onClickListener);
        fake3.setOnClickListener(onClickListener);
        fake4.setOnClickListener(onClickListener);
        progressBar.setOnFinishListener(new ProgressBar.OnFinishListener() {
            @Override
            public void onFinish() {
                finish();
                return;
            }
        });
        progressBar.goTo(100, TIME);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Integer.valueOf(((Button)v).getText().toString()) == current.getAnswer()){
                v.setBackgroundResource(R.drawable.toolbar_success);
                changeScore(Color.GREEN);
            }else{
                Vibrator vibr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibr.vibrate(100);
                v.setBackgroundResource(R.drawable.toolbar_fail);
                changeScore(Color.RED);
                if (SCORE < 0){
                    finish();
                    return;
                }
            }
            fake1.setEnabled(false);
            fake2.setEnabled(false);
            fake3.setEnabled(false);
            fake4.setEnabled(false);
            TIME -= 50;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadNextQuestion();
                }
            }, 700);
        }
    };

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


    ValueAnimator btnColorAnimation;
    private void changeScore(final int color){
        progressBar.goTo(0, 0);
        btnColorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), color, color);
        btnColorAnimation.setDuration(800);
        btnColorAnimation.setRepeatMode(ValueAnimator.REVERSE);
        btnColorAnimation.setRepeatCount(0);
        btnColorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                score_text.setTextColor((int) animator.getAnimatedValue());
                score_text.setShadowLayer(9f, 0, 0, color);
                score_text.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
            }

        });
        btnColorAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (color == Color.GREEN){
                    SCORE += 5;
                }else{
                    SCORE -= 5;
                }
                score_text.setText(NumberFormatting.englishToArabic("" + SCORE));
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                score_text.setTextColor(Color.WHITE);
                score_text.setShadowLayer(0f, 0, 0, Color.YELLOW);
                score_text.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        btnColorAnimation.start();
        animate(score_container).rotationY(180).setDuration(400).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animate(score_container).rotationY(0).setDuration(400).start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).start();

    }


    private void initQuestions(){
        questionList = QuestionHelper.getQuestions();
    }



    private void loadNextQuestion(){
        animate(question).translationX(-600).alpha(0).setDuration(700).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                IN_QUESTION++;
                current = questionList.get(IN_QUESTION);
                fake1.setBackgroundResource(R.drawable.toolbar);
                fake2.setBackgroundResource(R.drawable.toolbar);
                fake3.setBackgroundResource(R.drawable.toolbar);
                fake4.setBackgroundResource(R.drawable.toolbar);
                question.setText(NumberFormatting.englishToArabic(current.getStatement() + " = ?"));
                List<Integer> items = current.getAllOf();
                fake1.setText(NumberFormatting.englishToArabic("" + items.get(0)));
                fake2.setText(NumberFormatting.englishToArabic("" + items.get(1)));
                fake3.setText(NumberFormatting.englishToArabic("" + items.get(2)));
                fake4.setText(NumberFormatting.englishToArabic("" + items.get(3)));
                animate(question).translationX(600).setDuration(0).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        animate(question).alpha(1).translationX(0).setDuration(700).setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                fake1.setEnabled(true);
                                fake2.setEnabled(true);
                                fake3.setEnabled(true);
                                fake4.setEnabled(true);
                                progressBar.goTo(100, TIME);
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        }).start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).start();
    }
}
