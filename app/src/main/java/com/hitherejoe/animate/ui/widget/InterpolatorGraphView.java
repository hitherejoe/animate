package com.hitherejoe.animate.ui.widget;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

import com.hitherejoe.animate.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created on 21/05/2016.
 */
public class InterpolatorGraphView extends GraphView {

	private float accuracy;
	private Interpolator interpolator;

	public InterpolatorGraphView(Context context) {
		super(context);
	}

	public InterpolatorGraphView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public InterpolatorGraphView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void init() {
		super.init();

		getViewport().setXAxisBoundsManual(true);
		getViewport().setYAxisBoundsManual(true);
		getViewport().setMinX(0);
		getViewport().setMaxX(100);
		getViewport().setMinY(-20);
		getViewport().setMaxY(120);

		getGridLabelRenderer().setHorizontalAxisTitle(getContext().getString(R.string.time));
		getGridLabelRenderer().setVerticalAxisTitle(getContext().getString(R.string.progression));
		StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(this);
		staticLabelsFormatter.setHorizontalLabels(new String[] {"0%","", "", "", "", "100%"});
		staticLabelsFormatter.setVerticalLabels(new String[] {"","0%","", "", "", "", "100%" ,""});
		getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
		getGridLabelRenderer().setHorizontalAxisTitleColor(Color.BLACK);
		getGridLabelRenderer().setVerticalAxisTitleColor(Color.BLACK);
		getGridLabelRenderer().setHorizontalLabelsColor(Color.BLACK);
		getGridLabelRenderer().setVerticalLabelsColor(Color.BLACK);

		accuracy = 0.5f;
	}

	/**
	 * Set the accuracy of the graph
	 * @param accuracy - Lower is better, this graph will draw a point every <accuracy> x axis value
	 */
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	/**
	 * Draw over a graphView an interpolators graph
	 * @param interpolators - The interpolators to show
	 */
	public void applyInterpolator(@NonNull TimeInterpolator... interpolators) {
		removeAllSeries();

		boolean firstRound = true;
		for (TimeInterpolator interpolator : interpolators) {
			LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
			for (float i = 0; i <= 100; i += accuracy) {
				series.appendData(new DataPoint(i, 100 * interpolator.getInterpolation((i / 100f))), true, (int) (101f / accuracy));
			}

			if (interpolator instanceof GraphicInterpolator) {
				GraphicInterpolator graphicInterpolator = (GraphicInterpolator) interpolator;
				series.setColor(graphicInterpolator.getColor());
				series.setTitle(graphicInterpolator.getTitle());
				if (firstRound) {
					this.interpolator = graphicInterpolator.interpolatorInstance;
				}
			} else {
				series.setColor(Color.RED);
				if (firstRound) {
					this.interpolator = (Interpolator) interpolator;
				}
			}

			firstRound = false;
			addSeries(series);
		}

		getLegendRenderer().setVisible((interpolators.length > 1));
		getLegendRenderer().setTextColor(Color.BLACK);
		getLegendRenderer().setBackgroundColor(Color.TRANSPARENT);
		getLegendRenderer().setFixedPosition(0, 0);
	}

	public Interpolator getInterpolator() {
		return interpolator;
	}

	static public class GraphicInterpolator implements TimeInterpolator {
		private Interpolator interpolatorInstance;
		private String title;
		private int color;

		public GraphicInterpolator(Interpolator interpolatorInstance, String title, int color) {
			this.interpolatorInstance = interpolatorInstance;
			this.title = title;
			this.color = color;
		}

		public String getTitle() {
			return title;
		}

		public int getColor() {
			return color;
		}

		@Override
		public float getInterpolation(float input) {
			return interpolatorInstance.getInterpolation(input);
		}
	}
}
