package com.hitherejoe.animate.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.hitherejoe.animate.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MorphTransitionsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morph_transitions);
        ButterKnife.bind(this);
        setupToolbar();
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.button_morph)
    public void onButtonClick(View view) {
        Intent login = PopupActivity.getStartIntent(this, PopupActivity.MORPH_TYPE_BUTTON);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                (MorphTransitionsActivity.this, view, getString(R.string.transition_morph_view));
        startActivity(login, options.toBundle());
    }

    @OnClick(R.id.fab_morph)
    public void onFabClick(View view) {
        Intent login = PopupActivity.getStartIntent(this, PopupActivity.MORPH_TYPE_FAB);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                (MorphTransitionsActivity.this, view, getString(R.string.transition_morph_view));
        startActivity(login, options.toBundle());
    }
}
