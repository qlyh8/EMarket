package com.tistory.qlyh8.emarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.firebase.GetType;
import com.tistory.qlyh8.emarket.firebase.GetUserDB;
import com.tistory.qlyh8.emarket.trade.TradeMainActivity;
import com.tistory.qlyh8.emarket.trade.TradeStatusActivity;
import com.tistory.qlyh8.emarket.usePattern.UsePatternViewActivity;

//홈 (자가진단/거래하기/매칭현황/리포트/내정보 버튼)
public class MainActivity extends AppCompatActivity {

    private Button goUsePatternBtn;
    private Button goTradeListBtn;
    private Button goStatusBtn;
    private Button goReportBtn;
    private Button goProfileBtn;
    //private Button getDataSampleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

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
        /*String uid1 = "testProsumer_uid";
        GetDB.mUserRef.child(uid1).child("username").setValue("에디슨");        //
        GetDB.mUserRef.child(uid1).child("address").setValue("노원구");            //
        GetDB.mUserRef.child(uid1).child("addressNumber").setValue(1);            //
        GetDB.mUserRef.child(uid1).child("powerTrade").setValue(182);              //

        GetDB.mUserRef.child(uid1).child("powerUse").setValue(0);
        GetDB.mUserRef.child(uid1).child("phone").setValue("");
        GetDB.mUserRef.child(uid1).child("type").setValue("prosumer");
        GetDB.mUserRef.child(uid1).child("powerNumber").setValue("");
        GetDB.mUserRef.child(uid1).child("meterReadDate").setValue(1);
        GetDB.mUserRef.child(uid1).child("dueDate").setValue(25);
        GetDB.mUserRef.child(uid1).child("possibleTradeDate").setValue("1-10");
        GetDB.mUserRef.child(uid1).child("latitude").setValue(0);
        GetDB.mUserRef.child(uid1).child("longitude").setValue(0);*/

       /* GetDB.mEnrollRef.child("prosumer").child("testProsumer3_uid").child("testProsumer3_enroll_key1").child("name").setValue("testProsumer3");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer3_uid").child("testProsumer3_enroll_key1").child("ok").setValue("Y");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer3_uid").child("testProsumer3_enroll_key1").child("providePower").setValue("100");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer3_uid").child("testProsumer3_enroll_key1").child("enrollYear").setValue("2017");*/

        /*GetDB.mUserRef.child("testProsumer2_uid").child("phone").setValue("+821022222222");
        GetDB.mUserRef.child("testProsumer2_uid").child("type").setValue("prosumer");
        GetDB.mUserRef.child("testProsumer2_uid").child("address").setValue("");
        GetDB.mUserRef.child("testProsumer2_uid").child("powerNumber").setValue("");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer2_uid").child("testProsumer2_enroll_key1").child("name").setValue("testProsumer2");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer2_uid").child("testProsumer2_enroll_key1").child("ok").setValue("Y");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer2_uid").child("testProsumer2_enroll_key1").child("providePower").setValue("200");
        GetDB.mEnrollRef.child("prosumer").child("testProsumer2_uid").child("testProsumer2_enroll_key1").child("enrollYear").setValue("2017");*/

        /*GetDB.mUserRef.child("testConsumer3_uid").child("phone").setValue("+821033334444");
        GetDB.mUserRef.child("testConsumer3_uid").child("type").setValue("consumer");
        GetDB.mUserRef.child("testConsumer3_uid").child("address").setValue("");
        GetDB.mUserRef.child("testConsumer3_uid").child("powerNumber").setValue("");
        GetDB.mUserRef.child("testConsumer3_uid").child("powerTrade").setValue("90");
        GetDB.mUserRef.child("testConsumer3_uid").child("username").setValue("이지은");*/
        /*GetDB.mEnrollRef.child("consumer").child("testConsumer1_uid").child("testConsumer1_enroll_key1").child("name").setValue("testConsumer1");
        GetDB.mEnrollRef.child("consumer").child("testConsumer1_uid").child("testConsumer1_enroll_key1").child("ok").setValue("Y");
        GetDB.mEnrollRef.child("consumer").child("testConsumer1_uid").child("testConsumer1_enroll_key1").child("needPower").setValue("66");
        GetDB.mEnrollRef.child("consumer").child("testConsumer1_uid").child("testConsumer1_enroll_key1").child("enrollYear").setValue("2017");
        GetDB.mEnrollRef.child("consumer").child("testConsumer1_uid").child("testConsumer1_enroll_key1").child("needMonth").setValue("2");*/

        /*GetDB.mUserRef.child("testConsumer2_uid").child("phone").setValue("+821044444444");
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

        //유저 유형 가져오기
        GetType.isType();
        //유저 DB 가져오기
        GetUserDB.getThisUserDB();

        //자가진단
        goUsePatternBtn = (Button)findViewById(R.id.main_use_pattern_btn);
        goUsePatternBtn.setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(getBaseContext(), TradeMainActivity.class));
            }
        });

        //매칭현황
        goStatusBtn = (Button)findViewById(R.id.main_status_btn);
        goStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TradeStatusActivity.class));
            }
        });

        //리포트
        goReportBtn = (Button)findViewById(R.id.main_report_btn);
        goReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //내정보
        goProfileBtn = (Button)findViewById(R.id.main_profile_btn);
        goProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ProfileActivity.class));
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
    /*private void insertDataSample(String data1, String data2, int data3){
        Sample sample = new Sample(data1, data2, data3);
        GetDB.mDatabaseReference.child("SAMPLE").child(GetAuth.getGoogleUserId()).setValue(sample).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this,"데이터 저장",Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    /*private void getDataSample() {
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
    }*/
}
