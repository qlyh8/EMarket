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

import com.google.android.gms.tasks.OnSuccessListener;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.firebase.GetType;
import com.tistory.qlyh8.emarket.model.Enroll;

public class EnrollFormActivity extends AppCompatActivity {

    private EditText name;
    private EditText uniqueNumber;
    private EditText phone;
    private TextView address_text;
    private EditText address1;
    private EditText address2;
    private TextView power_text;
    private EditText power;
    private String agree1;
    private String agree2;
    private Button enrollFinalBtn;

    private String userType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_enroll_form);

        name = (EditText)findViewById(R.id.enroll_name);
        uniqueNumber = (EditText)findViewById(R.id.enroll_unique_number);
        phone = (EditText)findViewById(R.id.enroll_phone);
        address_text = (TextView)findViewById(R.id.enroll_address_text);
        address1 = (EditText)findViewById(R.id.enroll_address1);
        address2 = (EditText)findViewById(R.id.enroll_address2);
        power_text = (TextView)findViewById(R.id.enroll_power_text);
        power = (EditText)findViewById(R.id.enroll_power);
        enrollFinalBtn = (Button)findViewById(R.id.enroll_final_btn);

        setType();
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
        Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
        insertData("2017", "N", name.getText().toString(), uniqueNumber.getText().toString(), phone.getText().toString(), address1.getText().toString(), power.getText().toString(), "Y", "Y");

    }

    //객체 sample를 그대로 넣어주면 파이어베이스에 sample의 멤버 변수들이 등록
    private void insertData(String year, String ok, String name, String uniqueNumber, String phone, String address, String power, String agree1, String agree2){
        Enroll enrollData = new Enroll(year, ok, name, uniqueNumber, phone, address, power, agree1, agree2);
        GetDB.mDatabaseReference.child("enroll")
                                .child(userType)
                                .child(GetAuth.getGoogleUserId())
                                .push()
                                .setValue(enrollData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"데이터 저장",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
