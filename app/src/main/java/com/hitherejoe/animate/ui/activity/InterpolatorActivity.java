package com.hitherejoe.animate.ui.activity;

import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hitherejoe.animate.R;
import com.hitherejoe.animate.ui.widget.InterpolatorGraphView;

public class InterpolatorActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    private InterpolatorGraphView graph;
    private View robotTest;
    private View robotControl;
    private boolean animateToEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);

        graph = (InterpolatorGraphView) findViewById(R.id.interpolator_activity_graph);
        Spinner spinner = (Spinner) findViewById(R.id.interpolator_activity_spinner);
        robotTest = findViewById(R.id.interpolator_activity_robot_test);
        robotControl = findViewById(R.id.interpolator_activity_robot_control);

        assert spinner != null;
        assert graph != null;

        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.interpolators, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        graph.applyInterpolator(new OvershootInterpolator());
        setupToolbar();
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // 0: LinearInterpolator
        // 1: DecelerateInterpolator
        // 2: AccelerateInterpolator
        // 3: AccelerateDecelerateInterpolator
        // 4: FastOutLinearInInterpolator
        // 5: FastOutSlowInInterpolator
        // 6: LinearOutSlowInInterpolator
        // 7: OvershootInterpolator
        // 8: AnticipateInterpolator
        // 9: AnticipateOvershootInterpolator
        // 10: BounceInterpolator
        // 11: PathInterpolator

        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

        switch (position) {
            case 0:
                graph.applyInterpolator(new LinearInterpolator());
                break;
            case 1:
                graph.applyInterpolator(
                        new InterpolatorGraphView.GraphicInterpolator(new DecelerateInterpolator(1f), "Factor: 1 (default)", Color.RED),
                        new InterpolatorGraphView.GraphicInterpolator(new DecelerateInterpolator(1.4f), "Factor: 1.4", Color.BLUE),
                        new InterpolatorGraphView.GraphicInterpolator(new DecelerateInterpolator(0.6f), "Factor: 0.6", Color.DKGRAY)
                );
                break;
            case 2:
                graph.applyInterpolator(
                        new InterpolatorGraphView.GraphicInterpolator(new AccelerateInterpolator(1f), "Factor: 1 (default)", Color.RED),
                        new InterpolatorGraphView.GraphicInterpolator(new AccelerateInterpolator(1.4f), "Factor: 1.4", Color.BLUE),
                        new InterpolatorGraphView.GraphicInterpolator(new AccelerateInterpolator(0.6f), "Factor: 0.6", Color.DKGRAY)
                );
                break;
            case 3:
                graph.applyInterpolator(new AccelerateDecelerateInterpolator());
                break;
            case 4:
                graph.applyInterpolator(new FastOutLinearInInterpolator());
                break;
            case 5:
                graph.applyInterpolator(new FastOutSlowInInterpolator());
                break;
            case 6:
                graph.applyInterpolator(new LinearOutSlowInInterpolator());
                break;
            case 7:
                graph.applyInterpolator(
                        new InterpolatorGraphView.GraphicInterpolator(new OvershootInterpolator(2.0f), "Tension = 2 (default)", Color.RED),
                        new InterpolatorGraphView.GraphicInterpolator(new OvershootInterpolator(2.4f), "Tension = 2.4", Color.BLUE),
                        new InterpolatorGraphView.GraphicInterpolator(new OvershootInterpolator(1.6f), "Tension = 1.6", Color.DKGRAY)
                );
                break;
            case 8:
                graph.applyInterpolator(
                        new InterpolatorGraphView.GraphicInterpolator(new AnticipateInterpolator(2.0f), "Tension = 2 (default)", Color.RED),
                        new InterpolatorGraphView.GraphicInterpolator(new AnticipateInterpolator(2.4f), "Tension = 2.4", Color.BLUE),
                        new InterpolatorGraphView.GraphicInterpolator(new AnticipateInterpolator(1.6f), "Tension = 1.6", Color.DKGRAY)
                );
                break;
            case 9:
                graph.applyInterpolator(
                        new InterpolatorGraphView.GraphicInterpolator(new AnticipateOvershootInterpolator(3.0f), "Tension = 3 (default)", Color.RED),
                        new InterpolatorGraphView.GraphicInterpolator(new AnticipateOvershootInterpolator(3.4f), "Tension = 3.4", Color.BLUE),
                        new InterpolatorGraphView.GraphicInterpolator(new AnticipateOvershootInterpolator(2.6f), "Tension = 2.6", Color.DKGRAY)
                );
                break;
            case 10:
                graph.applyInterpolator(new BounceInterpolator());
                break;
            case 11:
                Path p = new Path();
                p.moveTo(0, 0);
                p.lineTo(0.8f, 0.2f);
                p.lineTo(1, 1);
                graph.applyInterpolator(
                        // http://cubic-bezier.com/#.55,.45,.86,-0.89
                        new InterpolatorGraphView.GraphicInterpolator(new PathInterpolator(0.55f, 0.45f, 0.86f, -0.89f), "Cubic (0.55, 0.45, 0.86, -0.89)", Color.RED),
                        // https://www.jasondavies.com/animated-bezier/
                        new InterpolatorGraphView.GraphicInterpolator(new PathInterpolator(0.6f, 0.2f), "Quadratic (0.6, 0.2)", Color.BLUE),
                        new InterpolatorGraphView.GraphicInterpolator(new PathInterpolator(p), "Path (0, 0; 0.8, 0.2; 1, 1)", Color.DKGRAY)
                );
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void activateAnimation() {
        if (!animateToEnd) {
            robotControl.animate().translationX(graph.getWidth() - robotControl.getWidth()).setInterpolator(graph.getInterpolator()).setDuration(1000);
            robotTest.animate().translationX(graph.getWidth() - robotControl.getWidth()).setInterpolator(new LinearInterpolator()).setDuration(1000);
            animateToEnd = true;
        } else {
            robotControl.animate().translationX(0).setInterpolator(graph.getInterpolator()).setDuration(1000);
            robotTest.animate().translationX(0).setInterpolator(new LinearInterpolator()).setDuration(1000);
            animateToEnd = false;
        }
    }

    public void onAnimate(View view) {
        activateAnimation();
    }
}
