package com.tistory.qlyh8.emarket.trade;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.MainActivity;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.firebase.GetMatchUser;
import com.tistory.qlyh8.emarket.firebase.GetType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//매칭현황
public class TradeStatusActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TradeStautsListAdapter listAdapter;
    private List<Integer> res;

    ImageView goPrevImg;

    // stage1_1, stage1_2, stage1_3, stage2_1, stage2_2, stage2_3;
    int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade_status);

        goPrevImg = (ImageView)findViewById(R.id.trade_status_prev);


        /*stage1_1 = (TextView)findViewById(R.id.trade_status_stage1_1);
        stage1_2 = (TextView)findViewById(R.id.trade_status_stage1_2);
        stage1_3 = (TextView)findViewById(R.id.trade_status_stage1_3);
        stage2_1 = (TextView)findViewById(R.id.trade_status_stage2_1);
        stage2_2 = (TextView)findViewById(R.id.trade_status_stage2_2);
        stage2_3 = (TextView)findViewById(R.id.trade_status_stage2_3);*/

        init();
    }

    public void init() {
        recyclerView = (RecyclerView)findViewById(R.id.trade_status_list);
        linearLayoutManager = new LinearLayoutManager(this);
        res = new ArrayList<>();

        GetDB.mEnrollRef.addValueEventListener(new ValueEventListener() {
            Calendar calendar = Calendar.getInstance();
            int thisYear = calendar.get(Calendar.YEAR);
            int thisMonth = calendar.get(Calendar.MONTH)+1;
            //int count = 0;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String[] data = snapshot.getKey().split("@@");  //2017@@11@11@@prosumerUid@@consumerUid@@

                    if(GetType.userType.equals("prosumer") && data.length == 5){
                        if(data[0].equals(thisYear+"") && data[1].equals(thisMonth+"") && data[3].equals(GetAuth.getUserId())){
                            //res.add(0);
                        }
                    }
                    if(GetType.userType.equals("consumer") && data.length == 5){
                        if(data[0].equals(thisYear+"") && data[1].equals(thisMonth+"") && data[4].equals(GetAuth.getUserId())){
                            //Toast.makeText(getApplicationContext(), data[0], Toast.LENGTH_SHORT).show();
                            //res.add(0);
                            //res.add(count++);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        //Toast.makeText(getApplicationContext(), ""+count, Toast.LENGTH_SHORT).show();
        res.add(0);
        res.add(1);
        /*res.add(0);
        res.add(0);*/

        listAdapter = new TradeStautsListAdapter(this, res);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listAdapter);


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

        /*switch (state){
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
        }*/
    }
}
