package com.tistory.qlyh8.emarket.status;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.tasks.OnSuccessListener;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.firebase.GetType;
import com.tistory.qlyh8.emarket.model.Enroll;

public class EnrollFormActivity extends AppCompatActivity {

    private int SEARCHADDRESS = 1000;

    private EditText name;
    private EditText uniqueNumber;
    private EditText phone;
    private TextView address_text;
    private EditText address;
    private Button findAddress;
    private TextView power_text;
    private EditText power;

    private Button enrollFinalBtn;

    private String userType;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_enroll_form);

        name = (EditText)findViewById(R.id.enroll_name);
        uniqueNumber = (EditText)findViewById(R.id.enroll_unique_number);
        phone = (EditText)findViewById(R.id.enroll_phone);
        address_text = (TextView)findViewById(R.id.enroll_address_text);
        address = (EditText)findViewById(R.id.enroll_address);
        findAddress = (Button)findViewById(R.id.enroll_find_address);
        findAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(EnrollFormActivity.this);
                    startActivityForResult(intent,SEARCHADDRESS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        power_text = (TextView)findViewById(R.id.enroll_power_text);
        power = (EditText)findViewById(R.id.enroll_power);
        enrollFinalBtn = (Button)findViewById(R.id.enroll_final_btn);

        setType();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == SEARCHADDRESS){
           if(resultCode == RESULT_OK){
               Place place = PlaceAutocomplete.getPlace(EnrollFormActivity.this, data);
               latitude = place.getLatLng().latitude;
               longitude = place.getLatLng().longitude;
               address.setText(place.getAddress());
           }
       }
    }

    public void goStatusEnroll(View v){
        Intent intent = new Intent(this, YearViewActivity.class);
        startActivity(intent);
        finish();
    }

    public void setType(){

        if(GetType.userType.equals("prosumer")){
            address_text.setText("장소(발전설비)");
            power_text.setText("발전설비용량");
            enrollFinalBtn.setText("등록하기");
            userType = "prosumer";
        }
        else if(GetType.userType.equals("consumer")){
            address_text.setText("주소");
            power_text.setText("요구발전량");
            enrollFinalBtn.setText("다음");
            userType = "consumer";
        }
        else{

        }
    }
    public void enrollComplete(View v){

        insertData("2017", "N", name.getText().toString(), uniqueNumber.getText().toString(), phone.getText().toString(), address.getText().toString(), power.getText().toString());
        goStatusEnroll(v);
    }

    //객체 sample를 그대로 넣어주면 파이어베이스에 sample의 멤버 변수들이 등록
    private void insertData(String year, String ok, String name, String uniqueNumber, String phone, String address, String power){
        Enroll enrollData = new Enroll(year, ok, name, uniqueNumber, phone, address, power,latitude,longitude);
        GetDB.mDatabaseReference.child("enroll")
                                .child(userType)
                                .child(GetAuth.getGoogleUserId())
                                .push()
                                .setValue(enrollData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"등록이 완료되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
