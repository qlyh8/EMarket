package com.tistory.qlyh8.emarket.initialize;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;

public class InitialPowerNumberActivity extends AppCompatActivity {

    EditText editNum1, editNum2, editNum3, editNum4;
    Button confirmBtn;
    String powerNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_power_num);

        editNum1 = (EditText)findViewById(R.id.initial_power_edit1);
        editNum2 = (EditText)findViewById(R.id.initial_power_edit2);
        editNum3 = (EditText)findViewById(R.id.initial_power_edit3);
        editNum4 = (EditText)findViewById(R.id.initial_power_edit4);
        confirmBtn = (Button)findViewById(R.id.inital_power_button);

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

                    userPowerNumRegister();
                    goNextActivity();
                }
            }
        });
    }

    public void goNextActivity(){
        startActivity(new Intent(this, InitialTypeActivity.class));
        finish();
    }

    public void userPowerNumRegister(){

        GetDB.mUserRef.child(GetAuth.getUserId()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GetDB.mUserRef.child(GetAuth.getUserId()).child("powerNumber").setValue(powerNum);
                GetDB.mUserRef.child(GetAuth.getUserId()).child("powerNumber").setValue("08-25-0130056-1501");
                GetDB.mUserRef.child(GetAuth.getUserId()).child("address").setValue("서울시 노원구 하계동 251-9");
                //GetDB.mUserRef.child(GetAuth.getUserId()).child("powerTrade").setValue("100");
                //Toast.makeText(getApplicationContext(), "완료되었습니다", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }
}
