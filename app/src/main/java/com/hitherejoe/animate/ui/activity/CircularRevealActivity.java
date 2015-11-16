package com.hitherejoe.animate.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.FragmentManager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

import com.hitherejoe.animate.ui.fragment.CircularRevealedFragment;
import com.hitherejoe.animate.R;
import com.hitherejoe.animate.util.ApiLevelHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CircularRevealActivity extends BaseActivity {

    @Bind(R.id.fab_reveal)
    FloatingActionButton mRevealButton;

    @Bind(R.id.frame_container)
    FrameLayout mFragmentContainer;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private Animator mCircularReveal;
    private Fragment mFragment;
    private Interpolator mInterpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);
        ButterKnife.bind(this);
        mInterpolator = new FastOutSlowInInterpolator();

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (mFragment != null) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove(mFragment);
            trans.commit();
            manager.popBackStack();
            mFragment = null;

            mRevealButton.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setListener(null)
                    .start();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.fab_reveal)
    public void onFabClick(View view) {
        mFragment = new CircularRevealedFragment();
        getFragmentManager().beginTransaction()
                .replace(mFragmentContainer.getId(), mFragment).commit();
        revealFragmentContainer(view, mFragmentContainer);
    }

    private void revealFragmentContainer(final View clickedView, final View fragmentContainer) {
        if (ApiLevelHelper.isAtLeast(Build.VERSION_CODES.LOLLIPOP)) {
            revealFragmentContainerLollipop(clickedView, fragmentContainer);
        } else {
            fragmentContainer.setVisibility(View.VISIBLE);
            clickedView.setVisibility(View.GONE);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void revealFragmentContainerLollipop(final View clickedView,
                                                 final View fragmentContainer) {
        prepareCircularReveal(clickedView, fragmentContainer);
        clickedView.animate()
                .scaleX(0f)
                .scaleY(0f)
                .setInterpolator(mInterpolator)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) { }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        fragmentContainer.setVisibility(View.VISIBLE);
                        mCircularReveal.start();
                        clickedView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) { }

                    @Override
                    public void onAnimationRepeat(Animator animation) { }
                })
                .start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void prepareCircularReveal(View startView, View targetView) {
        int centerX = (startView.getLeft() + startView.getRight()) / 2;
        int centerY = (startView.getTop() + startView.getBottom()) / 2;
        float finalRadius = (float) Math.hypot((double) centerX, (double) centerY);
        mCircularReveal = ViewAnimationUtils.createCircularReveal(
                targetView, centerX, centerY, 0, finalRadius);
        mCircularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCircularReveal.removeListener(this);
            }
        });
    }
}
