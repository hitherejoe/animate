package com.hitherejoe.animate.ui.activity;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ImageView;

import com.hitherejoe.animate.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimatedVectorDrawablesActivity extends BaseActivity {

    @Bind(R.id.image_twitter_heart)
    ImageView mTwitterHeartImage;

    @Bind(R.id.image_add_remove)
    ImageView mAddRemoveImage;

    private AnimatedVectorDrawable mAddToRemoveDrawable;
    private AnimatedVectorDrawable mRemoveToAddDrawable;
    private AnimatedVectorDrawable mHeartToTwitterDrawable;
    private AnimatedVectorDrawable mTwitterToHeartDrawable;

    private boolean mIsAddState;
    private boolean mIsTwitterState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_vector_drawables);
        ButterKnife.bind(this);
        setupToolbar();

        mAddToRemoveDrawable = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_add_to_remove);
        mRemoveToAddDrawable = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_remove_to_add);
        mHeartToTwitterDrawable =
                (AnimatedVectorDrawable) getDrawable(R.drawable.avd_heart_to_twitter);
        mTwitterToHeartDrawable =
                (AnimatedVectorDrawable) getDrawable(R.drawable.avd_twitter_to_heart);

        mIsAddState = true;
        mIsTwitterState = true;
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.image_add_remove)
    public void onAddRemoveImageClick() {
        AnimatedVectorDrawable drawable =
                mIsAddState ? mRemoveToAddDrawable : mAddToRemoveDrawable;
        mAddRemoveImage.setImageDrawable(drawable);
        drawable.start();
        mIsAddState = !mIsAddState;
    }

    @OnClick(R.id.image_twitter_heart)
    public void onTwitterHeartImageClick() {
        AnimatedVectorDrawable drawable =
                mIsTwitterState ? mHeartToTwitterDrawable : mTwitterToHeartDrawable;
        mTwitterHeartImage.setImageDrawable(drawable);
        drawable.start();
        mIsTwitterState = !mIsTwitterState;
    }

}
