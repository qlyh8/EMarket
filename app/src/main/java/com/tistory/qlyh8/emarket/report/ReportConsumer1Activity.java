package com.tistory.qlyh8.emarket.report;

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
import com.tistory.qlyh8.emarket.firebase.GetPowerUsed;
import com.tistory.qlyh8.emarket.firebase.GetUserDB;

import java.util.ArrayList;
import java.util.Calendar;

//프로슈머 월 청구서
public class ReportConsumer1Activity extends Fragment {

    private HorizontalBarChart barChart;

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

        dataMonth = getArguments().getInt("dataMonth");
        view = inflater.inflate(R.layout.report_item_consumer1, container, false);

        //graphText1 = view.findViewById(R.id.report_item1_graph_text1);
        //graphText2 = view.findViewById(R.id.report_item1_graph_text2);
        titleMonth = view.findViewById(R.id.report_item1_title_month);
        titlePower = view.findViewById(R.id.report_item1_title_trade_date);
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

        barChart = view.findViewById(R.id.report_horizontal);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(false);
        barChart.getDescription().setEnabled(false);
        barChart.setMaxVisibleValueCount(1000);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        barChart.setHovered(false);

        //차트 데이터 저장 영역
        setData();

        barChart.animateY(1000);
        barChart.getLegend().setEnabled(true); //하단의 범주 안보이게
        barChart.getXAxis().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(true);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setCenterAxisLabels(false);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if((int)value == 0){
                    //0이 안보이게
                    return "";
                }
                return String.valueOf(Integer.valueOf((int) value) + "kWh");
            }
        });
        barChart.getAxisRight().setEnabled(false);
    }

    private void setData() {

        GetUserDB.getThisUserDB();
        powerUsed = GetUserDB.thisUserDB.getPowerUse();

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        //차트에 들어갈 데이터 배열로 순서대로 임의의 데이터를 넣음
        //이 부분에서 firebase, 엑셀 데이터를 삽입
        if(powerUsed <= 200){
            yVals1.add(new BarEntry(0, new float[]{Float.valueOf(powerUsed), Float.valueOf(0),Float.valueOf(0)}));
        }
        else if((powerUsed > 200) && (powerUsed <= 400)){
            yVals1.add(new BarEntry(0, new float[]{Float.valueOf(200), Float.valueOf(powerUsed-200),Float.valueOf(0)}));
        }
        else{
            yVals1.add(new BarEntry(0, new float[]{Float.valueOf(200), Float.valueOf(200),Float.valueOf(powerUsed-400)}));
        }

        BarDataSet set1;

        set1 = new BarDataSet(yVals1, "");
        set1.setBarBorderWidth(1);
        set1.setDrawIcons(false);
        //여기서 차트 데이터의 색상을 정할 수 있음
        set1.setColors(new int[]{ContextCompat.getColor(getContext(),R.color.blueJeans),ContextCompat.getColor(getContext(),R.color.blueJeansDark),ContextCompat.getColor(getContext(),R.color.mint)});
        //아래 들어갈 범주
        set1.setStackLabels(new String[]{"1단계(0~200kWh)","2단계(200~400kWh)","3단계(400~)"});

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);

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
        titlePower.setText("2017.11.09");
        baseMoney.setText(GetPowerUsed.baseMoney + "원");
        powerMoney.setText((Double.toString(GetPowerUsed.powerMoney).split("\\.")[0]) + "원");
        basePowerMoney.setText((Double.toString(GetPowerUsed.basePowerMoney).split("\\.")[0]) + "원");
        etcMoney1.setText((Double.toString(GetPowerUsed.etc1Money).split("\\.")[0])  + "원");
        etcMoney2.setText((Double.toString(GetPowerUsed.etc2Money).split("\\.")[0]) + "원");
        totalMoney.setText((Double.toString(GetPowerUsed.totalMoney).split("\\.")[0]));
        saveMoney.setText((Double.toString(AfterTradeMoney).split("\\.")[0]) + "원");
    }
}
