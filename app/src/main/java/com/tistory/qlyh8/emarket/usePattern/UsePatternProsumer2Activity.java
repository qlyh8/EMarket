package com.tistory.qlyh8.emarket.usePattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.tistory.qlyh8.emarket.firebase.GetPowerUsed;
import com.tistory.qlyh8.emarket.firebase.GetUserDB;

import java.util.ArrayList;
import java.util.Calendar;

//프로슈머 월 추천거래량
public class UsePatternProsumer2Activity extends Fragment {

    private BarChart barChart;

    View view;
    int dataMonth;
    int powerUsed;  //사용량
    Double beforeTradeMoney;   //거래전 총금액
    Double AfterTradeMoney;   //거래후 총금액

    //TextView graphText1, graphText2;
    TextView titleMonth ,titlePower;
    TextView baseMoney, powerMoney, basePowerMoney, etcMoney1, etcMoney2, totalMoney;
    TextView saveMoney;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.use_pattern_item_prosumer2, container, false);

        //graphText1 = view.findViewById(R.id.use_pattern_item1_graph_text1);
        //graphText2 = view.findViewById(R.id.use_pattern_item1_graph_text2);
        titleMonth = view.findViewById(R.id.use_pattern_item2_title_month2);
        titlePower = view.findViewById(R.id.use_pattern_item2_title_power_use2);
        baseMoney = view.findViewById(R.id.use_pattern_item2_base_money2);
        powerMoney = view.findViewById(R.id.use_pattern_item2_power_money2);
        basePowerMoney = view.findViewById(R.id.use_pattern_item2_base_power_money2);
        etcMoney1 = view.findViewById(R.id.use_pattern_item2_etc1_money2);
        etcMoney2 = view.findViewById(R.id.use_pattern_item2_etc2_money2);
        totalMoney = view.findViewById(R.id.use_pattern_item2_total_money2);
        saveMoney = view.findViewById(R.id.use_pattern_item2_main_money);

        chartInit(view);
        setTextMoney();

        return view;
    }


    private void chartInit(View view){
        barChart = view.findViewById(R.id.barChart);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);

        barChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(1000);

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
                    return "잉여전력량";
                }else if(value == 2){
                    return "거래가능량";
                } else {
                    return "사용량";
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

        barChart.animateXY(2500, 2500);
    }

    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(0, GetUserDB.thisUserDB.powerUse));     //(프로슈머사용량(kWh))  --> 발전량 - 수전량 + 잉여량
        yVals1.add(new BarEntry(1, GetUserDB.thisUserDB.powerTrade));
        yVals1.add(new BarEntry(2, GetUserDB.thisUserDB.powerTrade));


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

    private  void setTextMoney(){

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;

        GetPowerUsed.calculatePowerUsed();
        beforeTradeMoney = GetPowerUsed.totalMoney;
        GetPowerUsed.calculateTradePowerUsed();
        AfterTradeMoney = beforeTradeMoney - GetPowerUsed.totalMoney;

        titleMonth.setText(year + "." + month);
        titlePower.setText(GetPowerUsed.totalPowerUsed + "KWh");
        baseMoney.setText(GetPowerUsed.baseMoney + "원");
        powerMoney.setText((Double.toString(GetPowerUsed.powerMoney).split("\\.")[0]) + "원");
        basePowerMoney.setText((Double.toString(GetPowerUsed.basePowerMoney).split("\\.")[0]) + "원");
        etcMoney1.setText((Double.toString(GetPowerUsed.etc1Money).split("\\.")[0])  + "원");
        etcMoney2.setText((Double.toString(GetPowerUsed.etc2Money).split("\\.")[0]) + "원");
        totalMoney.setText((Double.toString(GetPowerUsed.totalMoney).split("\\.")[0]));
        saveMoney.setText((Double.toString(AfterTradeMoney).split("\\.")[0]) + "원");
    }
}
