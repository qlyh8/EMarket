package com.tistory.qlyh8.emarket.usePattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.tistory.qlyh8.emarket.R;

import java.util.ArrayList;

public class UsePattern2Activity extends Fragment {

    private BarChart barChart;

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.use_pattern_item2, container, false);
        chartInit(view);
        return view;
    }


    private void chartInit(View view){
        barChart = (BarChart) view.findViewById(R.id.barChart);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);

        barChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        // barChart.setDrawYLabels(false);



        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(value == 1){
                    return "사용량";
                }else if(value == 2){
                    return "잉여전력량";
                } else {
                    return "거래가능량";
                }
            }
        });
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);




        barChart.getAxisLeft().setEnabled(false);


        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawGridLines(true);
        rightAxis.setLabelCount(3, false);

        rightAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(value<=200){
                    return "1단계";
                }else if(value<=400){
                    return "2단계";
                }else{
                    return "3단계";
                }
            }
        });
        rightAxis.setAxisMinimum(100f); // this replaces setStartAtZero(true)

        barChart.getLegend().setEnabled(false);
//        Legend l = barChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setFormSize(9f);
//        l.setTextSize(11f);
//        l.setXEntrySpace(4f);
//




        setData(2, 200);


    }

    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();


        yVals1.add(new BarEntry(0, 100));
        yVals1.add(new BarEntry(1, 250));
        yVals1.add(new BarEntry(2, 700));




        BarDataSet set1;

        set1 = new BarDataSet(yVals1, "");
        set1.setDrawIcons(false);
        set1.setColors(ColorTemplate.MATERIAL_COLORS);
        set1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (String.valueOf(value).split("\\.")[0])+"kWh";
            }
        });

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);

        data.setBarWidth(0.9f);

        barChart.setData(data);

    }

}
