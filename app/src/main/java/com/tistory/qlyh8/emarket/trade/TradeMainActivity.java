package com.tistory.qlyh8.emarket.trade;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.LoadingActivity;
import com.tistory.qlyh8.emarket.MainActivity;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.firebase.GetType;
import com.tistory.qlyh8.emarket.model.User;

import java.util.ArrayList;
import java.util.List;

//거래하기
public class TradeMainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LoadingActivity loading;
    Runnable runnable;
    Handler handler;

    List<String> nameData, powerData;
    RecyclerView recyclerlist;
    LinearLayoutManager linearLayoutManager;
    TradeListAdapter1 tradeListAdapter1;
    TradeListAdapter2 tradeListAdapter2;

    ImageView goPrevImg, myImg;
    TextView myNameText, myPowerText, typeText;
    Button startMatchingBtn;
    String typeStr = "", powerStr = "";
    User currentUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade_main);

        goPrevImg = (ImageView)findViewById(R.id.trade_prev);
        myImg = (ImageView) findViewById(R.id.trade_main_my_img);
        myNameText = (TextView)findViewById(R.id.trade_main_my_name_text);
        myPowerText = (TextView)findViewById(R.id.trade_main_my_power_text);
        typeText = (TextView)findViewById(R.id.trade_main_type_text);
        startMatchingBtn = (Button)findViewById(R.id.trade_main_btn);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run(){
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "매칭이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                final TradeMatchDialog tradeMatchDialog = new TradeMatchDialog(TradeMainActivity.this);
                tradeMatchDialog.show();
            }
        };
        loading = new LoadingActivity(this);

        init();
        mapInit();
        currentUserInit();
        buttonInit();
    }

    private void init() {

        recyclerlist = (RecyclerView)findViewById(R.id.trade_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerlist.setLayoutManager(linearLayoutManager);

        nameData = new ArrayList<>();
        powerData = new ArrayList<>();

        GetDB.mUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            int count = 0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    //different type user
                    if(!GetType.userType.equals(snapshot.child("type").getValue())){
                        ++count;
                        nameData.add(snapshot.child("username").getValue().toString() + typeStr);
                        powerData.add(snapshot.child("powerTrade").getValue().toString() + powerStr);

                        //current user is prosumer
                        //if(dataSnapshot.child("type").getValue().equals("consumer")
                        //        && (Integer)dataSnapshot.child("powerTrade").getValue() <= currentUserData.getPowerTrade()){
                            //possibleTradeItem.add(dataSnapshot.child("username").getValue().toString());
                        //}
                        //current user is consumer
                        //else if(dataSnapshot.child("type").getValue().equals("prosumer")
                        //        && (Integer)dataSnapshot.child("powerTrade").getValue() >= currentUserData.getPowerTrade()){
                            //possibleTradeItem.add(dataSnapshot.child("username").getValue().toString());
                        //}
                        //else{}
                    }
                }

                tradeListAdapter1 = new TradeListAdapter1(getApplicationContext(), nameData, powerData);
                tradeListAdapter2 = new TradeListAdapter2(getApplicationContext(), nameData, powerData);
                if(GetType.userType.equals("prosumer")){
                    recyclerlist.setAdapter(tradeListAdapter2);
                }
                else {
                    recyclerlist.setAdapter(tradeListAdapter1);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    private void mapInit() {
        SupportMapFragment mapFragment  = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.trade_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        setUserMarker(googleMap);
    }

    private void setUserMarker(final GoogleMap googleMap) {
        GetDB.mUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    User userData = snapshot.getValue(User.class);
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(userData.getLatitude(), userData.getLongitude())));

                    if(snapshot.getKey().equals(GetAuth.getUserId())){
                        //Toast.makeText(getApplicationContext(), userData.getPhone(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    private void currentUserInit(){
        GetDB.mUserRef.child(GetAuth.getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(GetType.userType.equals("prosumer")){
                    myImg.setImageResource(R.drawable.trade_prosumer);
                    myNameText.setText(dataSnapshot.child("username").getValue() + " 프로슈머님");
                    myPowerText.setText(dataSnapshot.child("powerTrade").getValue() + "KWh 이하 거래량");
                    typeText.setText("컨슈머 현재 등록 현황");
                    typeStr = " 컨슈머님";
                    powerStr = "KWh 이상 거래량";
                }
                else{
                    myImg.setImageResource(R.drawable.trade_consumer);
                    myNameText.setText(dataSnapshot.child("username").getValue() + " 컨슈머님");
                    myPowerText.setText(dataSnapshot.child("powerTrade").getValue() + "KWh 이상 거래량");
                    typeText.setText("프로슈머 현재 등록 현황");
                    typeStr = " 프로슈머님";
                    powerStr = "KWh 이하 거래량";
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    private void buttonInit(){
        goPrevImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        startMatchingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.show();
                Toast.makeText(getApplicationContext(), "매칭 중입니다...", Toast.LENGTH_LONG).show();
                handler.postDelayed(runnable, 5000);
            }
        });
    }
}
