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

import com.google.android.gms.maps.CameraUpdateFactory;
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
import com.tistory.qlyh8.emarket.firebase.GetMatchUser;
import com.tistory.qlyh8.emarket.firebase.GetType;
import com.tistory.qlyh8.emarket.firebase.GetUserDB;
import com.tistory.qlyh8.emarket.model.User;

import java.util.ArrayList;
import java.util.List;

//거래하기
public class TradeMainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LoadingActivity loading, loading2;
    Runnable runnable;
    Handler handler;

    List<String> nameData, powerData;
    RecyclerView recyclerlist;
    LinearLayoutManager linearLayoutManager;
    TradeListAdapter1 tradeListAdapter1;
    TradeListAdapter2 tradeListAdapter2;

    ImageView goPrevImg, myImg;
    TextView myNameText, myPowerText, typeText1, typeText2;
    Button startMatchingBtn;
    String typeStr = "", powerStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade_main);

        goPrevImg = (ImageView)findViewById(R.id.trade_prev);
        //myImg = (ImageView) findViewById(R.id.trade_main_my_img);
        myNameText = (TextView)findViewById(R.id.trade_main_my_name_text);
        myPowerText = (TextView)findViewById(R.id.trade_main_my_power_text);
        typeText1 = (TextView)findViewById(R.id.trade_main_type_text1);
        typeText2 = (TextView)findViewById(R.id.trade_main_type_text2);
        startMatchingBtn = (Button)findViewById(R.id.trade_main_btn);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run(){
                loading.dismiss();
                //Toast.makeText(getApplicationContext(), "매칭이 완료되었습니다!", Toast.LENGTH_SHORT).show();
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

        loading2 = new LoadingActivity(this);

        recyclerlist = (RecyclerView)findViewById(R.id.trade_list);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //linearLayoutManager.setReverseLayout(false);
        //linearLayoutManager.setStackFromEnd(false);

        recyclerlist.setLayoutManager(linearLayoutManager);

        nameData = new ArrayList<>();
        powerData = new ArrayList<>();

        loading2.show();
        GetDB.mUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            int count = 0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    //different type user & same address number
                    if(!GetType.userType.equals(snapshot.child("type").getValue())
                            && (GetUserDB.thisUserDB.addressNumber == snapshot.child("addressNumber").getValue(Integer.class))){
                        ++count;
                        typeStr = " 님";
                        powerStr = "KWh";
                        nameData.add(snapshot.child("username").getValue().toString() + typeStr);
                        powerData.add(snapshot.child("powerTrade").getValue().toString() + powerStr);
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
                loading2.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
        recyclerlist.setNestedScrollingEnabled(false);
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
                    LatLng marker = new LatLng(userData.getLatitude(), userData.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(userData.getLatitude(), userData.getLongitude())));

                    if(snapshot.getKey().equals(GetAuth.getUserId())){
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                        googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(marker, 13.0f) );
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
                    //myImg.setImageResource(R.drawable.trade_prosumer);
                    myNameText.setText(dataSnapshot.child("username").getValue() + " 프로슈머님");
                    myPowerText.setText(dataSnapshot.child("powerTrade").getValue() + "KWh 이하부터 거래가 가능합니다");
                    typeText1.setText("거래 가능한 컨슈머 현재 등록 현황");
                    typeText2.setText("해당 전력량(KWh) 이상부터 거래가 가능합니다");
                    typeStr = " 님";
                    powerStr = "KWh 이상";
                }
                else{
                    //myImg.setImageResource(R.drawable.trade_consumer);
                    myNameText.setText(dataSnapshot.child("username").getValue() + " 컨슈머님");
                    myPowerText.setText(dataSnapshot.child("powerTrade").getValue() + "KWh 이상부터 거래가 가능합니다");
                    typeText1.setText("거래 가능한 프로슈머 현재 등록 현황");
                    typeText2.setText("해당 전력량(KWh) 이하까지 거래가 가능합니다");
                    typeStr = " 님";
                    powerStr = "KWh 이하";
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
                findBestMatchingUser();
                //Toast.makeText(getApplicationContext(), GetMatchUser.matchingUserUid, Toast.LENGTH_SHORT).show();
                handler.postDelayed(runnable, 5000);
            }
        });
    }

    /*   매칭하기 - 리스트로 모든 가능한 거래자 받기
    * 1. same addressNumber
      2. 프로슈머: powerProvide <= 200  & 컨슈머: powerUse > 400
      3. |프로슈머의 powerTrade - 컨슈머의 powerTrade| 최소값 (0에 가까울수록 최적)
    * */
    private void findBestMatchingUser(){

        GetDB.mUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            int min = 1000;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    User userData = snapshot.getValue(User.class);

                    if(GetUserDB.thisUserDB.addressNumber == userData.getAddressNumber() && !GetAuth.getUserId().equals(snapshot.getKey().toString())){
                        if(GetUserDB.thisUserDB.getType().equals("prosumer") && userData.getType().equals("consumer")){
                            if(GetUserDB.thisUserDB.getPowerProvide() <= 200 && userData.getPowerUse() > 400) {
                                if(min > Math.abs(GetUserDB.thisUserDB.getPowerTrade() - userData.getPowerTrade())){
                                    min = Math.abs(GetUserDB.thisUserDB.getPowerTrade() - userData.getPowerTrade());
                                    GetMatchUser.matchingUserUid = snapshot.getKey().toString();
                                    GetMatchUser.matchingUserName = userData.getUsername();
                                    GetMatchUser.matchingUserType = userData.getType();
                                    GetMatchUser.matchingUserPowerTrade = userData.getPowerTrade();
                                    GetMatchUser.calculateUserSaveMoney(userData.getPowerTrade());
                                    GetDB.mUserRef.child(GetAuth.getUserId()).child("matching").child("username").setValue(userData.getUsername());
                                    //Toast.makeText(getApplicationContext(), ""+userData.getUsername(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        if(GetUserDB.thisUserDB.getType().equals("consumer") && userData.getType().equals("prosumer")){
                            if(GetUserDB.thisUserDB.getPowerUse() > 400 && userData.getPowerProvide() <= 200){
                                if(min > Math.abs(GetUserDB.thisUserDB.getPowerTrade() - userData.getPowerTrade())){
                                    min = Math.abs(GetUserDB.thisUserDB.getPowerTrade() - userData.getPowerTrade());
                                    GetMatchUser.matchingUserUid = snapshot.getKey().toString();
                                    GetMatchUser.matchingUserName = userData.getUsername();
                                    GetMatchUser.matchingUserType = userData.getType();
                                    GetMatchUser.matchingUserPowerTrade = userData.getPowerTrade();
                                    GetMatchUser.calculateUserSaveMoney(userData.getPowerTrade());
                                    GetDB.mUserRef.child(GetAuth.getUserId()).child("matching").child("username").setValue(userData.getUsername());
                                    //Toast.makeText(getApplicationContext(), ""+userData.getUsername(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
                if(min == 1000){
                    Toast.makeText(getApplicationContext(), "추천할 거래자가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }


}
