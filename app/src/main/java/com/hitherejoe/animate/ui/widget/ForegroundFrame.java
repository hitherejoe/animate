package com.hitherejoe.animate.ui.widget;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.widget.FrameLayout;

import com.hitherejoe.animate.R;
import com.hitherejoe.animate.ui.activity.ObjectAnimatorActivity;

public class ForegroundFrame extends FrameLayout {

    private static final int DELAY_COLOR_CHANGE = 750;

    public ForegroundFrame(Context context) {
        super(context);
        addContentView(this);
    }

    public ForegroundFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
        addContentView(this);
    }

    public ForegroundFrame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addContentView(this);
    }

    public ForegroundFrame(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        addContentView(this);
    }

    public void doStuff() {
        resizeView();
        moveViewOffScreen();
        final int backgroundColor = R.color.primary;
        animateForegroundColor(ContextCompat.getColor(getContext(), backgroundColor));
    }

    private void addContentView(FrameLayout container) {
        inflate(getContext(), R.layout.layout_frame, this);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_quiz);
        floatingActionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPropertyAnimatorCompat animatorCompat = buildAnimation(v);
                animatorCompat.setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        doStuff();
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                });
            }
        });
    }

    private ViewPropertyAnimatorCompat buildAnimation(View view) {
        ViewPropertyAnimatorCompat animatorCompat = ViewCompat.animate(view);
        animatorCompat.alpha(0f);
        animatorCompat.scaleX(0f).scaleY(0f);
        return animatorCompat;
    }

    private final Property<ForegroundFrame, Integer> FOREGROUND_COLOR =
            new IntProperty<ForegroundFrame>("foregroundColor") {

                @SuppressLint("NewApi")
                @Override
                public void setValue(ForegroundFrame layout, int value) {
                    if (layout.getForeground() instanceof ColorDrawable) {
                        ((ColorDrawable) layout.getForeground().mutate()).setColor(value);
                    } else {
                        layout.setForeground(new ColorDrawable(value));
                    }
                }

                @SuppressLint("NewApi")
                @Override
                public Integer get(ForegroundFrame layout) {
                    if (layout.getForeground() instanceof ColorDrawable) {
                        return ((ColorDrawable) layout.getForeground()).getColor();
                    } else {
                        return Color.TRANSPARENT;
                    }
                }
            };

    private void resizeView() {
        final float widthHeightRatio = (float) getHeight() / (float) getWidth();
        // Animate X and Y scaling separately to allow different start delays.
        // object animators for x and y with different durations and then run them independently
        resizeViewProperty(View.SCALE_X, .5f, 200);
        resizeViewProperty(View.SCALE_Y, .5f / widthHeightRatio, 250);
    }


    private void resizeViewProperty(Property<View, Float> property,
                                    float targetScale, int durationOffset) {
        ObjectAnimator animator =
                ObjectAnimator.ofFloat(this, property, 1f, targetScale);
        animator.setInterpolator(new LinearOutSlowInInterpolator());
        animator.setStartDelay(DELAY_COLOR_CHANGE + durationOffset);
        animator.start();
    }

    private void animateForegroundColor(@ColorInt final int targetColor) {
        ObjectAnimator animator = ObjectAnimator.ofInt(this, FOREGROUND_COLOR,
                Color.TRANSPARENT, targetColor);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setStartDelay(DELAY_COLOR_CHANGE);
        animator.start();
    }

    private void moveViewOffScreen() {
        // Move the current view off the screen.
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if (getContext() instanceof ObjectAnimatorActivity) {
                    ((ObjectAnimatorActivity) getContext()).showNext();
                }
            }
        };
        new Handler().postDelayed(r,
                DELAY_COLOR_CHANGE * 2);
    }

    public abstract class IntProperty<T> extends Property<T, Integer> {

        public IntProperty(String name) {
            super(Integer.class, name);
        }

        /**
         * A type-specific override of the {@link #set(Object, Integer)} that is faster when dealing
         * with fields of type <code>int</code>.
         */
        public abstract void setValue(T object, int value);

        @Override
        final public void set(T object, Integer value) {
            //noinspection UnnecessaryUnboxing
            setValue(object, value.intValue());
        }

    }
}
