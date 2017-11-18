package com.tistory.qlyh8.emarket.report;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
import java.util.List;
import java.util.Random;

//리포트 프로슈머 거래이력
public class ReportProsumer2Activity extends Fragment {

    private View view;
    private com.github.mikephil.charting.charts.LineChart mChart;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ReportProsumerItem recyclerAdapter;
    private List<String> res;
    private List<Integer> resMoney1, resMoney2, resMoney3;

    //spinner
    private ArrayAdapter<CharSequence> option;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.report_item_prosumer2, container, false);
        chartInit();
        recyclerInit();
        init();

        return view;
    }

    private void init(){

        spinner = view.findViewById(R.id.spinner2);

        //옵션은 string에 array
        option = ArrayAdapter.createFromResource(getContext(), R.array.option, R.layout.spinner_item_prosumer);
        option.setDropDownViewResource(R.layout.spinner_dropdown_item_prosumer);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //option.getItem(position); 현재 선택 데이터
                //여기에서 리사이클러뷰 데이터 년도별로 넣어주고
                //res.clear() <- 초기화
                //res.add <- 데이터 추가

                recyclerAdapter.notifyDataSetChanged(); //리스트의 어댑터 갱신 ★ 마지막에 꼭 해줘야함
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner.setAdapter(option);
    }

    private void recyclerInit(){
        res = new ArrayList<>();
        //더미데이터
        res.add("");res.add("");res.add("");res.add("");res.add("");res.add("");
        res.add("");res.add("");res.add("");res.add("");res.add("");res.add("");

        resMoney1 = new ArrayList<>();
        resMoney2 = new ArrayList<>();
        resMoney3 = new ArrayList<>();
        //더미데이터
        for(int i = 0 ; i < 12 ; i++){
            resMoney1.add(1);
            resMoney2.add(1);
            resMoney3.add(1);
        }

        recyclerView = view.findViewById(R.id.report_prosumer_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new ReportProsumerItem(getContext(), res, resMoney1, resMoney2, resMoney3);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void chartInit(){
        mChart = view.findViewById(R.id.report_prosumer_line_chart);
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
        mChart.setMaxVisibleValueCount(1000);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setPinchZoom(true);
        mChart.setScaleMinima(1f,0.1f);// 차트 하단 너비

        // add data
        setData(12);

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
                return (String.valueOf(value).split("\\.")[0])+"월";
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
                return (String.valueOf(value).split("\\.")[0])+"만원";
            }
        });

        //우측 범주 안보이게
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    private void setData(int count) {

        //맨 아래 데이터
        ArrayList<Entry> yVals1 = new ArrayList<>();
        //데이터 등록
        for (int i = 1; i <= count; i++) {
            float val = (new Random().nextInt()%10)+20;
            yVals1.add(new Entry(i, val));
        }

        //두번째 데이터
        ArrayList<Entry> yVals2 = new ArrayList<>();
        //데이터 등록
        for (int i = 1; i <= count; i++) {
            float val = (new Random().nextInt()%10)+50;
            yVals2.add(new Entry(i, val));

        }

        LineDataSet set1, set2;

        set1 = new LineDataSet(yVals1, "거래 전 요금");

        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(ContextCompat.getColor(getContext(), R.color.material_blue_500));
        set1.setCircleColor(ContextCompat.getColor(getContext(), R.color.material_blue_500)); // 첫번째 데이터 동그라미 색
        set1.setLineWidth(3f);
        set1.setCircleRadius(3f);
        set1.setFillAlpha(255);
        set1.setDrawValues(false);
        set1.setFillColor(ContextCompat.getColor(getContext(), R.color.material_blue_500));
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);


        set2 = new LineDataSet(yVals2, "절약 요금");
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        set2.setColor(ContextCompat.getColor(getContext(), R.color.material_green_500));
        set2.setCircleColor(ContextCompat.getColor(getContext(), R.color.material_green_500)); // 두번째 데이터 동그라미 색
        set2.setLineWidth(3f);
        set2.setCircleRadius(3f);
        set2.setFillAlpha(100);
        set2.setDrawValues(false);

        set2.setFillColor(ContextCompat.getColor(getContext(), R.color.material_green_500));
        set2.setDrawCircleHole(false);
        set2.setHighLightColor(ContextCompat.getColor(getContext(), R.color.material_green_500)); //눌렀을때 나오는 선의 색


        LineData data = new LineData(set1, set2);
        //차트위에 숫자 색상
        data.setValueTextColor(Color.BLACK);
        data.setValueTextSize(9f);
        mChart.setData(data);
    }
}
