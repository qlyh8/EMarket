package com.tistory.qlyh8.emarket.report;

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

//리포트 프로슈머 청구서
public class ReportProsumer1Activity extends Fragment {

    private BarChart barChart;

    View view;
    int dataMonth;
    int powerUsed;  //사용량
    Double beforeTradeMoney;   //거래전 총금액
    Double AfterTradeMoney;   //거래후 총금액

    //TextView graphText1, graphText2;
    TextView titleMonth ,titleDate;
    TextView baseMoney, powerMoney, basePowerMoney, etcMoney1, etcMoney2, totalMoney;
    TextView saveMoney;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.report_item_prosumer1, container, false);

        //graphText1 = view.findViewById(R.id.report_item1_graph_text1);
        //graphText2 = view.findViewById(R.id.report_item1_graph_text2);
        titleMonth = view.findViewById(R.id.report_item1_title_month);
        titleDate = view.findViewById(R.id.report_item1_title_trade_date);
        baseMoney = view.findViewById(R.id.report_item1_base_money);
        powerMoney = view.findViewById(R.id.report_item1_power_money);
        basePowerMoney = view.findViewById(R.id.report_item1_base_power_money);
        etcMoney1 = view.findViewById(R.id.report_item1_etc1_money);
        etcMoney2 = view.findViewById(R.id.report_item1_etc2_money);
        totalMoney = view.findViewById(R.id.report_item1_total_money);
        saveMoney = view.findViewById(R.id.report_item1_main_money);

        chartInit(view);
        setTextMoney();

        return view;
    }


    private void chartInit(View view){
        barChart = view.findViewById(R.id.report_bar_chart);

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
                if(value == 1){   //value == 1
                    return "한전수전량";
                }else if(value == 2){   //value == 2
                    return "잉여전력량";
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
                }else if(value >200 && value<=400){
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

        barChart.animateXY(1500, 1500);
    }

    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(0, GetUserDB.thisUserDB.powerUse));     //사용량
        yVals1.add(new BarEntry(1, GetUserDB.thisUserDB.powerProvide));   //한전수전량
        yVals1.add(new BarEntry(2, GetUserDB.thisUserDB.powerTrade));   //잉여전력량


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
        int month = calendar.get(Calendar.MONTH);

        GetPowerUsed.calculatePowerUsed();
        beforeTradeMoney = GetPowerUsed.totalMoney;
        GetPowerUsed.calculateTradePowerUsed();
        AfterTradeMoney = beforeTradeMoney - GetPowerUsed.totalMoney;

        titleMonth.setText(year + "." + month);
        titleDate.setText("2017.11.09");
        baseMoney.setText(GetPowerUsed.baseMoney + "원");
        powerMoney.setText((Double.toString(GetPowerUsed.powerMoney).split("\\.")[0]) + "원");
        basePowerMoney.setText((Double.toString(GetPowerUsed.basePowerMoney).split("\\.")[0]) + "원");
        etcMoney1.setText((Double.toString(GetPowerUsed.etc1Money).split("\\.")[0])  + "원");
        etcMoney2.setText((Double.toString(GetPowerUsed.etc2Money).split("\\.")[0]) + "원");
        totalMoney.setText((Double.toString(GetPowerUsed.totalMoney).split("\\.")[0]));
        saveMoney.setText((Double.toString(AfterTradeMoney).split("\\.")[0]) + "원");
    }
}
