package com.tistory.qlyh8.emarket.usePattern;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tistory.qlyh8.emarket.R;

import java.util.ArrayList;
import java.util.Random;

public class UsePattern4Activity extends Fragment {

    private com.github.mikephil.charting.charts.LineChart mChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.use_pattern_item3, container, false);
        chartInit(view);
        return view;
    }

    private void chartInit(View view){
        mChart = (com.github.mikephil.charting.charts.LineChart) view.findViewById(R.id.lineChart);
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });

        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setMaxVisibleValueCount(200);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setPinchZoom(true);
        mChart.setScaleMinima(1f,0.1f);// 차트 하단 너비


        // add data
        setData(31);

        mChart.animateX(2500);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();


        //아래쪽 범주 설정
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);



        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(11f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (String.valueOf(value).split("\\.")[0])+"일";
            }
        });


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (String.valueOf(value).split("\\.")[0])+"kWh";
            }
        });

        //우측 범주 안보이게
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    private void setData(int count) {

        //맨 아래 데이터
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        //데이터 등록
        for (int i = 0; i < count; i++) {
            float val = (new Random().nextInt()%10)+20;
            yVals1.add(new Entry(i, val));
        }

        //두번째 데이터
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        //데이터 등록
        for (int i = 0; i < count; i++) {
            float val = (new Random().nextInt()%10)+50;
            yVals2.add(new Entry(i, val));

        }

        //세번째 데이터
        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
        //데이터 등록
        for (int i = 0; i < count; i++) {
            float val = (new Random().nextInt()%10)+100;
            yVals3.add(new Entry(i, val));
        }

        LineDataSet set1, set2, set3;

        set1 = new LineDataSet(yVals1, "한전수급량");

        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(ContextCompat.getColor(getContext(), R.color.material_green_500));
        set1.setCircleColor(ContextCompat.getColor(getContext(), R.color.material_green_500)); // 첫번째 데이터 동그라미 색
        set1.setLineWidth(3f);
        set1.setCircleRadius(3f);
        set1.setFillAlpha(255);
        set1.setDrawValues(false);
        set1.setFillColor(ContextCompat.getColor(getContext(), R.color.material_green_500));
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);



        set2 = new LineDataSet(yVals2, "사용량");
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        set2.setColor(ContextCompat.getColor(getContext(), R.color.material_lime_a700));
        set2.setCircleColor(ContextCompat.getColor(getContext(), R.color.material_lime_a700)); // 두번째 데이터 동그라미 색
        set2.setLineWidth(3f);
        set2.setCircleRadius(3f);
        set2.setFillAlpha(100);
        set2.setDrawValues(false);

        set2.setFillColor(ContextCompat.getColor(getContext(), R.color.material_lime_a700));
        set2.setDrawCircleHole(false);
        set2.setHighLightColor(ContextCompat.getColor(getContext(), R.color.material_lime_a700)); //눌렀을때 나오는 선의 색


        set3 = new LineDataSet(yVals3, "발전량");
        set3.setAxisDependency(YAxis.AxisDependency.LEFT);
        set3.setColor(ContextCompat.getColor(getContext(), R.color.material_red_500));
        set3.setCircleColor(ContextCompat.getColor(getContext(), R.color.material_red_500)); //세번째 데이터 동그라미 색
        set3.setLineWidth(3f);
        set3.setCircleRadius(3f);
        set3.setFillAlpha(50);
        set3.setDrawValues(false);

        set3.setFillColor(ContextCompat.getColor(getContext(), R.color.material_red_500));
        set3.setDrawCircleHole(false);
        set3.setHighLightColor(ContextCompat.getColor(getContext(), R.color.material_red_500));


        LineData data = new LineData(set1, set2, set3);
        //차트위에 숫자 색상
        data.setValueTextColor(Color.BLACK);
        data.setValueTextSize(9f);
        mChart.setData(data);

    }

}
