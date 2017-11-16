package com.tistory.qlyh8.emarket.trade;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tistory.qlyh8.emarket.MainActivity;
import com.tistory.qlyh8.emarket.R;

//매칭현황
public class TradeStatusActivity extends AppCompatActivity {

    ImageView goPrevImg;
    TextView matchingName, matchingPowerTrade, matchingPowerRecommend, matchingSaveMoney;
    TextView stage1_1, stage1_2, stage1_3, stage2_1, stage2_2, stage2_3;

    int state = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade_status);

        goPrevImg = (ImageView)findViewById(R.id.trade_status_prev);
        matchingName = (TextView)findViewById(R.id.trade_status_username);
        matchingPowerTrade = (TextView)findViewById(R.id.trade_status_powerTrade);
        matchingPowerRecommend = (TextView)findViewById(R.id.trade_status_powerRecommend);
        matchingSaveMoney = (TextView)findViewById(R.id.trade_status_saveMoney);
        stage1_1 = (TextView)findViewById(R.id.trade_status_stage1_1);
        stage1_2 = (TextView)findViewById(R.id.trade_status_stage1_2);
        stage1_3 = (TextView)findViewById(R.id.trade_status_stage1_3);
        stage2_1 = (TextView)findViewById(R.id.trade_status_stage2_1);
        stage2_2 = (TextView)findViewById(R.id.trade_status_stage2_2);
        stage2_3 = (TextView)findViewById(R.id.trade_status_stage2_3);

        init();
    }

    public void init() {
        goPrevImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TradeStatusActivity.this, MainActivity.class));
                finish();
            }
        });

        //현재 월에 해당되는 매칭 신청 정보 가져오기
        //
        //matchingName.setText("dd");
        //matchingPowerTrade.setText("aa");
        //matchingPowerRecommend.setText("cc");
        //matchingSaveMoney.setText("ee");

        switch (state){
            case 1:
                stage1_1.setTextColor(Color.rgb(250,88,70));
                break;
            case 2:
                stage1_1.setTextColor(Color.rgb(176,176,176));
                stage1_2.setTextColor(Color.rgb(250,88,70));
                break;
            case 3:
                stage1_1.setTextColor(Color.rgb(176,176,176));
                stage1_2.setTextColor(Color.rgb(176,176,176));
                stage1_3.setTextColor(Color.rgb(250,88,70));
                break;
            case 4:
                stage1_1.setTextColor(Color.rgb(176,176,176));
                stage1_2.setTextColor(Color.rgb(176,176,176));
                stage1_3.setTextColor(Color.rgb(176,176,176));
                stage2_1.setTextColor(Color.rgb(250,88,70));
                break;
            case 5:
                stage1_1.setTextColor(Color.rgb(176,176,176));
                stage1_2.setTextColor(Color.rgb(176,176,176));
                stage1_3.setTextColor(Color.rgb(176,176,176));
                stage2_1.setTextColor(Color.rgb(176,176,176));
                stage2_2.setTextColor(Color.rgb(250,88,70));
                break;
            case 6:
                stage1_1.setTextColor(Color.rgb(176,176,176));
                stage1_2.setTextColor(Color.rgb(176,176,176));
                stage1_3.setTextColor(Color.rgb(176,176,176));
                stage2_1.setTextColor(Color.rgb(176,176,176));
                stage2_2.setTextColor(Color.rgb(176,176,176));
                stage2_3.setTextColor(Color.rgb(250,88,70));
                break;
            default:
                break;
        }
    }
}
