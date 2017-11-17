package com.tistory.qlyh8.emarket.usePattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.tistory.qlyh8.emarket.R;

import java.util.ArrayList;

public class UsePattern1Activity extends Fragment {

    private String patternType;
    private HorizontalBarChart barChart;

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        patternType = getArguments().getString("patternType");
        view = inflater.inflate(R.layout.use_pattern_item1, container, false);
        chartInit(view);
        return view;
    }

    private void chartInit(View view){
        barChart = (HorizontalBarChart)view.findViewById(R.id.horizontal);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(false);
        barChart.getDescription().setEnabled(false);
        barChart.setMaxVisibleValueCount(400);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        barChart.setHovered(false);

        //차트 데이터 저장 영역!
        setData();

        barChart.animateY(1000);
        barChart.getLegend().setEnabled(true); //하단의 범주 안보이게
        barChart.getXAxis().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setCenterAxisLabels(false);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if((int)value == 0){
                    //0이 안보이게!
                    return "";
                }
                return String.valueOf(Integer.valueOf((int) value) + "kWh");
            }
        });
        barChart.getAxisRight().setEnabled(false);


    }

    private void setData() {

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        //차트에 들어갈 데이터 배열로 순서대로 임의의 데이터를 넣었어!
        //이 부분에서 firebase, 엑셀 데이터를 삽입하면 될거같아!!
        yVals1.add(new BarEntry(0, new float[]{Float.valueOf(200), Float.valueOf(200),Float.valueOf(55)}));

        BarDataSet set1;

        set1 = new BarDataSet(yVals1, "");
        set1.setBarBorderWidth(3);
        set1.setDrawIcons(false);
        //여기서 차트 데이터의 색상을 정할 수 있어!
        set1.setColors(new int[]{ContextCompat.getColor(getContext(),R.color.material_red_500),ContextCompat.getColor(getContext(),R.color.material_lime_a700),ContextCompat.getColor(getContext(),R.color.material_green_500)});
        //아래 들어갈 범주
        set1.setStackLabels(new String[]{"1단계(0~200kWh)","2단계(200~400kWh)","3단계(400~)"});


        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);


        barChart.setData(data);

    }
}
