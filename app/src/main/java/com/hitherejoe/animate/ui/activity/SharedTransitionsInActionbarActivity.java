package com.hitherejoe.animate.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.transition.Slide;
import android.view.Gravity;

import com.hitherejoe.animate.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SharedTransitionsInActionbarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_transitions_in_actionbar);
        ButterKnife.bind(this);

        Slide slide = new Slide(Gravity.BOTTOM);
        slide.addTarget(R.id.text_detail);
        slide.addTarget(R.id.text_close);
        slide.addTarget(R.id.view_separator);
        getWindow().setEnterTransition(slide);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.text_close)
    public void onCloseTextClicked() {
        finishAfterTransition();
    }

}
