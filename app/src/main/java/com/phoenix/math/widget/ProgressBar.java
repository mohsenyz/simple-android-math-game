package com.phoenix.math.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.phoenix.math.helper.MathUtils;

/**
 * Created by dahlia on 2/25/17.
 */
public class ProgressBar extends View {
    private int precent = 100;
    private int[] colors = new int[]{Color.parseColor("#581845"),Color.YELLOW, Color.RED, Color.GREEN, Color.parseColor("#FF00FF") };
    int def_color = colors[0];
    public ProgressBar(Context c){
        super(c);
    }
    public ProgressBar(Context c, AttributeSet a){
        super(c, a);
    }
    public ProgressBar(Context c, AttributeSet a, int def){
        super(c, a, def);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setAntiAlias(true);
        p.setColor(def_color);
        p.setShadowLayer(5, 0, 0, Color.BLACK);
        setLayerType(LAYER_TYPE_SOFTWARE, p);
        double pres = 100 * precent;
        double width = ((double) getWidth()) / pres;
        Log.d("pres","" + pres);
        canvas.drawRect(0, 0, (int)width, getHeight() - 5, p);
        super.onDraw(canvas);
    }

    ValueAnimator va;
    public void goTo(final int pre, int speed){
        if (va != null && va.isRunning()){
            va.cancel();
        }
        if (pre ==0 && speed == 0){
            precent = 0;
            invalidate();
        }
        va = ValueAnimator.ofInt(precent, pre);
        va.setDuration(speed);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                precent = (int)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                def_color = colors[MathUtils.rand(0, 4)];
                if (onFinishListener != null)
                    onFinishListener.onFinish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        va.start();
    }


    public interface OnFinishListener{
        void onFinish();
    }

    OnFinishListener onFinishListener;

    public void setOnFinishListener(OnFinishListener onFinishListener){
        this.onFinishListener = onFinishListener;
    }




    public void goTo(final int pre){
        goTo(pre, 700);
    }
}
