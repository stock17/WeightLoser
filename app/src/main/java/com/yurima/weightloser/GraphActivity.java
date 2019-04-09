package com.yurima.weightloser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.yurima.weightloser.weight.WeightDbAdapter;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.data;

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

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {

        });



        Iterator<Map.Entry<Date, Double>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Date, Double> pair = it.next();
            Calendar c = Calendar.getInstance();
            c.setTime(pair.getKey());
            int day = c.get(Calendar.DAY_OF_MONTH);
            double val = pair.getValue();
            series.appendData(new DataPoint(day, val), false, 7);
        }

        graphView.addSeries(series);





    }
}
