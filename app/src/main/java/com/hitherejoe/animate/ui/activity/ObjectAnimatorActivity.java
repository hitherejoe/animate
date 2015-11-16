package com.hitherejoe.animate.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.AdapterViewAnimator;

import com.hitherejoe.animate.R;
import com.hitherejoe.animate.ui.adapter.FrameAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ObjectAnimatorActivity extends BaseActivity {

    @Bind(R.id.flipper_content)
    AdapterViewAnimator mContentFlipper;

    private boolean isAnimatingUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
        ButterKnife.bind(this);

        isAnimatingUp = true;
        mContentFlipper.setAdapter(new FrameAdapter(this, 20));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void showNext() {
        setAnimations();
        mContentFlipper.showNext();
        isAnimatingUp = !isAnimatingUp;
    }

    private void setAnimations() {
        mContentFlipper.setInAnimation(this, isAnimatingUp
                ? R.animator.slide_in_bottom : R.animator.slide_in_left);
        mContentFlipper.setOutAnimation(this, isAnimatingUp
                ? R.animator.slide_out_top : R.animator.slide_out_right);
    }

}
