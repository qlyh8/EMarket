package com.tistory.qlyh8.emarket;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.firebase.GetType;
import com.tistory.qlyh8.emarket.model.Enroll;
import com.tistory.qlyh8.emarket.status.TradeStatusActivity;
import com.tistory.qlyh8.emarket.status.YearViewActivity;
import com.tistory.qlyh8.emarket.model.Sample;
import com.tistory.qlyh8.emarket.usePattern.UsePatternViewActivity;

import java.util.Calendar;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private Button usePatternBtn;
    private Button goTradeListBtn;
    private Button goYearBtn;
    private Button goProfileBtn;
    private Button getDataSampleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        mapInit();

        /*String name="1", email="2", uid="3", phone="4";
        boolean emailVerified=false;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();
            phone = user.getPhoneNumber();
            // Check if user's email is verified
            emailVerified = user.isEmailVerified();

            uid = user.getUid();
        }
        Toast.makeText(getApplicationContext(), "name:"+name+"\nemail:"+email+"\nverf:"+emailVerified+"\nuid:"+uid+"\nphone"+phone, Toast.LENGTH_LONG).show();
        */
        /*GetDB.mUserRef.child("testProsumer1_uid").child("phone").setValue("+821011111111");
        GetDB.mUserRef.child("testProsumer1_uid").child("type").setValue("prosumer");
        GetDB.mUserRef.child("testProsumer1_uid").child("address").setValue("");
        GetDB.mUserRef.child("testProsumer1_uid").child("powerNumber").setValue("");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer1_uid").child("testProsumer1_enroll_key1").child("name").setValue("testProsumer1");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer1_uid").child("testProsumer1_enroll_key1").child("ok").setValue("Y");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer1_uid").child("testProsumer1_enroll_key1").child("providePower").setValue("100");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer1_uid").child("testProsumer1_enroll_key1").child("enrollYear").setValue("2017");

        GetDB.mUserRef.child("testProsumer2_uid").child("phone").setValue("+821022222222");
        GetDB.mUserRef.child("testProsumer2_uid").child("type").setValue("prosumer");
        GetDB.mUserRef.child("testProsumer2_uid").child("address").setValue("");
        GetDB.mUserRef.child("testProsumer2_uid").child("powerNumber").setValue("");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer2_uid").child("testProsumer2_enroll_key1").child("name").setValue("testProsumer2");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer2_uid").child("testProsumer2_enroll_key1").child("ok").setValue("Y");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer2_uid").child("testProsumer2_enroll_key1").child("providePower").setValue("200");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer2_uid").child("testProsumer2_enroll_key1").child("enrollYear").setValue("2017");

        GetDB.mUserRef.child("testConsumer1_uid").child("phone").setValue("+821033333333");
        GetDB.mUserRef.child("testConsumer1_uid").child("type").setValue("consumer");
        GetDB.mUserRef.child("testConsumer1_uid").child("address").setValue("");
        GetDB.mUserRef.child("testConsumer1_uid").child("powerNumber").setValue("");
        GetDB.mEnrollRef.child("consumer").child("testConsumer1_uid").child("testConsumer1_enroll_key1").child("name").setValue("testConsumer1");
        GetDB.mEnrollRef.child("consumer").child("testConsumer1_uid").child("testConsumer1_enroll_key1").child("ok").setValue("Y");
        GetDB.mEnrollRef.child("consumer").child("testConsumer1_uid").child("testConsumer1_enroll_key1").child("needPower").setValue("66");
        GetDB.mEnrollRef.child("consumer").child("testConsumer1_uid").child("testConsumer1_enroll_key1").child("enrollYear").setValue("2017");
        GetDB.mEnrollRef.child("consumer").child("testConsumer1_uid").child("testConsumer1_enroll_key1").child("needMonth").setValue("2");

        GetDB.mUserRef.child("testConsumer2_uid").child("phone").setValue("+821044444444");
        GetDB.mUserRef.child("testConsumer2_uid").child("type").setValue("consumer");
        GetDB.mUserRef.child("testConsumer2_uid").child("address").setValue("");
        GetDB.mUserRef.child("testConsumer2_uid").child("powerNumber").setValue("");
        GetDB.mEnrollRef.child("consumer").child("testConsumer2_uid").child("testConsumer2_enroll_key1").child("name").setValue("testConsumer2");
        GetDB.mEnrollRef.child("consumer").child("testConsumer2_uid").child("testConsumer2_enroll_key1").child("ok").setValue("Y");
        GetDB.mEnrollRef.child("consumer").child("testConsumer2_uid").child("testConsumer2_enroll_key1").child("needPower").setValue("12");
        GetDB.mEnrollRef.child("consumer").child("testConsumer2_uid").child("testConsumer2_enroll_key1").child("enrollYear").setValue("2017");
        GetDB.mEnrollRef.child("consumer").child("testConsumer2_uid").child("testConsumer2_enroll_key1").child("needMonth").setValue("5");*/

        /*GetDB.mDatabaseReference.child("trade").child("test_trade1").child("prosumer").setValue("");
        GetDB.mDatabaseReference.child("trade").child("test_trade1").child("consumer").setValue("");
        GetDB.mDatabaseReference.child("trade").child("test_trade1").child("state").setValue("1");*/

        /*Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);
        int thisMonth = calendar.get(Calendar.MONTH)+1;
        int thisDay = calendar.get(Calendar.DAY_OF_MONTH);

        GetDB.mEnrollRef.child("prosumer").child("testProsumer1_uid").child("testProsumer1_enroll_key1").child("enrollMonth").setValue(thisMonth);
        GetDB.mEnrollRef.child("prosumer").child("testProsumer1_uid").child("testProsumer1_enroll_key1").child("enrollDay").setValue(thisDay);*/
    }

    private void init() {

        GetType.isType();

        //자가진단
        usePatternBtn = (Button)findViewById(R.id.main_use_pattern_btn);
        usePatternBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insertDataSample("sample1", "sample2", 123);
                startActivity(new Intent(getBaseContext(), UsePatternViewActivity.class));
            }
        });

        //거래하기
        goTradeListBtn = (Button)findViewById(R.id.main_trade_btn);
        goTradeListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), TradeListActivity.class));
            }
        });

        //매칭현황
        goYearBtn = (Button)findViewById(R.id.main_year);
        goYearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, YearViewActivity.class));
            }
        });


        //내정보
        goProfileBtn = (Button)findViewById(R.id.main_profile_btn);
        goProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ProfileActivity.class));
                //Toast.makeText(getBaseContext(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //파이어베이스 데이터 삽입, 가져오기 샘플 버튼
        /*getDataSampleBtn = (Button)findViewById(R.id.get);
        getDataSampleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getDataSample();
            }
        });*/
    }

    //객체 sample를 그대로 넣어주면 파이어베이스에 sample의 멤버 변수들이 등록
    private void insertDataSample(String data1, String data2, int data3){
        Sample sample = new Sample(data1, data2, data3);
        GetDB.mDatabaseReference.child("SAMPLE").child(GetAuth.getGoogleUserId()).setValue(sample).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this,"데이터 저장",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataSample() {
        //파이어베이스에서 데이터를 가지고오는 로직이
        //addListenerForSingleValueEvent 는 딱 한번 호출할때 사용
        //.addValueEventListener 로 바꿔주면 DB의 데이터가 바뀌면 자동으로 아래 함수들을 계속해서 호출

        //.child("SAMPLE").child(GetAuth.getUserId()) 샘플/유저아이디 아래 있는 데이터들을 읽어오는 로직
        GetDB.mDatabaseReference.child("SAMPLE").child(GetAuth.getGoogleUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Sample sample = dataSnapshot.getValue(Sample.class);
                Toast.makeText(MainActivity.this,sample.getData1() + " " + sample.getData2() + " " + sample.getData3(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void mapInit() {
        SupportMapFragment mapFragment  = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        setUserMarker(googleMap);
    }

    private void setUserMarker(final GoogleMap googleMap) {
        //GetDB.mEnrollRef.child("prosumer").addValueEventListener(new ValueEventListener() {
        GetDB.mEnrollRef.child("prosumer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                        Enroll enroll = childSnapshot.getValue(Enroll.class);
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(enroll.getLatitude(), enroll.getLongitude())));
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }
}
