package com.tistory.qlyh8.emarket.trade;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.firebase.GetMatchUser;
import com.tistory.qlyh8.emarket.firebase.GetType;
import com.tistory.qlyh8.emarket.firebase.GetUserDB;

import java.util.Calendar;

//추천 거래자 팝업
public class TradeMatchDialog extends Dialog {

    Context context;

    public TradeMatchDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    private TextView usernameText, powerTradeText, powerRecommendText, saveMoneyText;
    private Button submitBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade_match_list);

        //기본 액티비티랑 사용법이 같은 다이얼로그
        //여기에 파이어베이스로 데이터를 가져와야 함
        usernameText = findViewById(R.id.trade_match_username);
        powerTradeText = findViewById(R.id.trade_match_powerTrade);
        powerRecommendText = findViewById(R.id.trade_match_powerRecommend);
        saveMoneyText = findViewById(R.id.trade_match_saveMoney);
        submitBtn = findViewById(R.id.trade_match_submit_btn);
        cancelBtn = findViewById(R.id.trade_match_cancel_btn);

        //알고리즘 만들어서 추천 대상자 뽑아내기
        if(GetType.userType.equals("prosumer")){
            usernameText.setText(GetMatchUser.matchingUserName + " 컨슈머");
            powerTradeText.setText(GetMatchUser.matchingUserPowerTrade + "KWh 이상 거래량");
            powerRecommendText.setText(GetUserDB.thisUserDB.getPowerTrade() + "KWh 이하 전력량");
            saveMoneyText.setText(GetMatchUser.userSaveMoney + "원");
        }
        else{
            usernameText.setText(GetMatchUser.matchingUserName + " 프로슈머");
            powerTradeText.setText(GetMatchUser.matchingUserPowerTrade + "KWh 이하 거래량");
            powerRecommendText.setText(GetUserDB.thisUserDB.getPowerTrade() + "KWh 이상 전력량");
            saveMoneyText.setText(GetMatchUser.userSaveMoney + "원");
        }

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int thisYear = calendar.get(Calendar.YEAR);
                int thisMonth = calendar.get(Calendar.MONTH)+1;
                int thisDay = calendar.get(Calendar.DAY_OF_MONTH);

                String enrollDate = thisYear + "-" + thisMonth + "-" + thisDay;
                String enrollId = thisYear + "@@" + thisMonth + "@@" + thisDay + "@@";

                if(GetType.userType.equals("prosumer")){
                    enrollId += GetAuth.getUserId() + "@@" + GetMatchUser.matchingUserUid + "@@";
                    GetDB.mEnrollRef.child(enrollId).child("prosumer").setValue(GetAuth.getUserId());
                    GetDB.mEnrollRef.child(enrollId).child("consumer").setValue(GetMatchUser.matchingUserUid);
                    GetDB.mEnrollRef.child(enrollId).child("date").setValue(enrollDate);
                    GetDB.mEnrollRef.child(enrollId).child("prosumerOk").setValue("Y");
                    GetDB.mEnrollRef.child(enrollId).child("consumerOk").setValue("N");
                    GetDB.mEnrollRef.child(enrollId).child("state").setValue(1);
                }
                else{
                    enrollId += GetMatchUser.matchingUserUid + "@@" + GetAuth.getUserId() + "@@";
                    GetDB.mEnrollRef.child(enrollId).child("consumer").setValue(GetAuth.getUserId());
                    GetDB.mEnrollRef.child(enrollId).child("prosumer").setValue(GetMatchUser.matchingUserUid);
                    GetDB.mEnrollRef.child(enrollId).child("date").setValue(enrollDate);
                    GetDB.mEnrollRef.child(enrollId).child("prosumerOk").setValue("N");
                    GetDB.mEnrollRef.child(enrollId).child("consumerOk").setValue("Y");
                    GetDB.mEnrollRef.child(enrollId).child("state").setValue(1);
                }

                Toast.makeText(getContext(), "거래를 신청했습니다", Toast.LENGTH_SHORT).show();

                dismiss();
                context.startActivity(new Intent(context, TradeStatusActivity.class));
                ((Activity)context).finish();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
