package com.yurima.weightloser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.yurima.weightloser.weight.WeightDbAdapter;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphActivity extends AppCompatActivity {

    WeightDbAdapter mDbAdapter = new WeightDbAdapter(this);
    Map<Date, Double> data;

    @BindView(R.id.graph_weight)
    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        ButterKnife.bind(this);

        mDbAdapter.openDB();
        data = mDbAdapter.getItems();

        DataPoint[] points = new DataPoint[data.size()];
        Iterator<Map.Entry<Date, Double>> it = data.entrySet().iterator();
        int n = 0;
        while (it.hasNext()) {
            Map.Entry<Date, Double> pair = it.next();
            points[n] = new DataPoint(pair.getKey(), pair.getValue());
            n++;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
        graphView.addSeries(series);

        graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graphView.getGridLabelRenderer().setNumHorizontalLabels(3);
        graphView.getViewport().setMinX(points[0].getX());
        graphView.getViewport().setMaxX(points[data.size() - 1].getX());
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getGridLabelRenderer().setHumanRounding(false);
    }
}
