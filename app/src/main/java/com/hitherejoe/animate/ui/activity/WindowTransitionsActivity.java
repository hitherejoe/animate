package com.hitherejoe.animate.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.hitherejoe.animate.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WindowTransitionsActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_transitions);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.text_fade_fast)
    public void startFadeFastTransition() {
        Intent intent = new Intent(WindowTransitionsActivity.this, TransitionInActivity.class);
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_FADE_FAST);
        startActivityWithOptions(intent);
    }

    @OnClick(R.id.text_fade_slow)
    public void startFadeSlowTransition() {
        Intent intent = new Intent(WindowTransitionsActivity.this, TransitionInActivity.class);
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_FADE_SLOW);
        startActivityWithOptions(intent);
    }

    @OnClick(R.id.text_slide_right)
    public void startSlideRightTransition() {
        Intent intent = new Intent(WindowTransitionsActivity.this, TransitionInActivity.class);
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_SLIDE_RIGHT);
        startActivityWithOptions(intent);
    }

    @OnClick(R.id.text_slide_bottom)
    public void startSlideBottomTransition() {
        Intent intent = new Intent(WindowTransitionsActivity.this, TransitionInActivity.class);
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_SLIDE_BOTTOM);
        startActivityWithOptions(intent);
    }

    @OnClick(R.id.text_explode)
    public void startExplodeTransition() {
        Intent intent = new Intent(WindowTransitionsActivity.this, TransitionInActivity.class);
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_EXPLODE);
        startActivityWithOptions(intent);
    }

    @OnClick(R.id.text_explode_bounce)
    public void startExplodeBounceTransition() {
        Intent intent = new Intent(WindowTransitionsActivity.this, TransitionInActivity.class);
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_EXPLODE_BOUNCE);
        startActivityWithOptions(intent);
    }

    private void startActivityWithOptions(Intent intent) {
        ActivityOptions transitionActivity =
                ActivityOptions.makeSceneTransitionAnimation(WindowTransitionsActivity.this);
        startActivity(intent, transitionActivity.toBundle());
    }

}
