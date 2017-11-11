package com.tistory.qlyh8.emarket;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.firebase.GetType;
import com.tistory.qlyh8.emarket.model.Enroll;
import com.tistory.qlyh8.emarket.status.YearViewActivity;
import com.tistory.qlyh8.emarket.model.Sample;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private Button goYearBtn;

    private Button insertDataSampleBtn;
    private Button getDataSampleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        mapInit();
    }

    private void init() {

        GetType.isType();

        goYearBtn = (Button)findViewById(R.id.main_year);
        goYearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, YearViewActivity.class));
            }
        });

        //파이어베이스 데이터 삽입, 가져오기 샘플 버튼
        insertDataSampleBtn = (Button)findViewById(R.id.insert);
        getDataSampleBtn = (Button)findViewById(R.id.get);

        insertDataSampleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDataSample("sample1", "sample2", 123);
            }
        });

        getDataSampleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataSample();
            }
        });
    }

    private void mapInit() {
        SupportMapFragment mapFragment  = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
