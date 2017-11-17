package com.tistory.qlyh8.emarket.initialize;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.model.User;

import java.util.Calendar;

//전력량계 번호 입력 (주소, 최초등록일, 경도, 위도, 전력량계번호, 거래추천량, 소비량, 이름이 DB에 입력)
public class InitialPowerNumberActivity extends AppCompatActivity {

    EditText editNum1, editNum2, editNum3, editNum4;
    Button confirmBtn;
    String powerNum;

    User userData = null;

    int SEARCHADDRESS = 1000;
    String address = "";
    double latitude = 0;
    double longitude = 0;

    //Calendar calendar = Calendar.getInstance();
    //int initialYear = calendar.get(Calendar.YEAR);
    //int initialMonth = calendar.get(Calendar.MONTH)+1;
    //int initialDay = calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_power_num);

        editNum1 = (EditText)findViewById(R.id.initial_power_edit1);
        editNum2 = (EditText)findViewById(R.id.initial_power_edit2);
        editNum3 = (EditText)findViewById(R.id.initial_power_edit3);
        editNum4 = (EditText)findViewById(R.id.initial_power_edit4);
        confirmBtn = (Button)findViewById(R.id.inital_power_button);

        init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SEARCHADDRESS){
            if(resultCode == RESULT_OK){
                Place place = PlaceAutocomplete.getPlace(InitialPowerNumberActivity.this, data);
                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;
                address = place.getAddress().toString();
            }
        }
    }

    //확인버튼
    public void init(){
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( editNum1.getText().toString().equals("")
                        || editNum2.getText().toString().equals("")
                        || editNum3.getText().toString().equals("")
                        || editNum4.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "모두 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
                else{
                    powerNum = editNum1.getText().toString() + "-"
                            + editNum2.getText().toString() + "-"
                            + editNum3.getText().toString() + "-"
                            + editNum4.getText().toString();

                    try {
                        Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(InitialPowerNumberActivity.this);
                        startActivityForResult(intent, SEARCHADDRESS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    userPowerNumRegister();

                }
            }
        });
    }

    public void goNextActivity(){
        startActivity(new Intent(this, InitialTypeActivity.class));
        finish();
    }

    public void userPowerNumRegister(){

        GetDB.mUserRef.child(GetAuth.getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userData = dataSnapshot.getValue(User.class);
                insertData1(address, 0, latitude, longitude, "", 0, 0, 0, "홍길동");
                insertData2(25, 1, "1-10");
                goNextActivity();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    //객체 sample를 그대로 넣어주면 파이어베이스에 sample의 멤버 변수들이 등록
    public void insertData1(String address, int addressNumber, double latitude, double longitude, String powerNumber, int powerProvide, int powerTrade, int powerUse, String username) {
        User setUserData = new User(address, addressNumber, latitude, longitude, powerNumber, powerProvide, powerTrade, powerUse, username);
        GetDB.mDatabaseReference.child("user")
                .child(GetAuth.getUserId())
                .setValue(setUserData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //Toast.makeText(getApplicationContext(),"등록이 완료되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void insertData2(int dueDate, int meterReadDate, String possibleTradeDate) {
        //User setUserData = new User(dueDate, meterReadDate, possibleTradeDate);
        GetDB.mDatabaseReference.child("user")
                .child(GetAuth.getUserId()).child("dueDate").setValue(dueDate);
        GetDB.mDatabaseReference.child("user")
                .child(GetAuth.getUserId()).child("meterReadDate").setValue(meterReadDate);
        GetDB.mDatabaseReference.child("user")
                .child(GetAuth.getUserId()).child("possibleTradeDate").setValue(possibleTradeDate);
                //Toast.makeText(getApplicationContext(),"등록이 완료되었습니다.",Toast.LENGTH_SHORT).show();


    }
}
