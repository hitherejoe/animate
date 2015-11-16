package com.hitherejoe.animate.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.ActionBar;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hitherejoe.animate.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InterpolatorActivity extends BaseActivity {

    @Bind(R.id.spinner_interpolators)
    Spinner mInterpolatorSpinner;

    @Bind(R.id.fab_interpolator)
    FloatingActionButton mFloatingActionButton;

    private boolean mIsButtonAtTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        ButterKnife.bind(this);
        mIsButtonAtTop = true;
        setupSpinnerAdapter();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setupSpinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.item_spinner, getResources().getStringArray(R.array.interpolators));
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        mInterpolatorSpinner.setAdapter(adapter);
    }

    @OnClick(R.id.text_animate)
    public void animate() {
        //TODO: Get height from screen
        int height = 1725;
        if (!mIsButtonAtTop) height = -height;
        mIsButtonAtTop = !mIsButtonAtTop;
        mFloatingActionButton.animate().setInterpolator(getSelectedInterpolator())
                .setDuration(500)
                .setStartDelay(200)
                .translationYBy(height)
                .start();
    }

    private Interpolator getSelectedInterpolator() {
        switch (mInterpolatorSpinner.getSelectedItemPosition()) {
            case 1:
                return new FastOutLinearInInterpolator();
            case 2:
                return new FastOutSlowInInterpolator();
            case 3:
                return new LinearOutSlowInInterpolator();
            case 4:
                return new AccelerateDecelerateInterpolator();
            case 5:
                return new AccelerateInterpolator();
            case 6:
                return new DecelerateInterpolator();
            case 7:
                return new AnticipateInterpolator();
            case 8:
                return new AnticipateOvershootInterpolator();
            case 9:
                return new BounceInterpolator();
            case 10:
                return new LinearInterpolator();
            case 11:
                return new OvershootInterpolator();
            default:
                return null;
        }
    }
}
