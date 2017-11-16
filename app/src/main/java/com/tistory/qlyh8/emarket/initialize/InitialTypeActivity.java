package com.tistory.qlyh8.emarket.initialize;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tistory.qlyh8.emarket.MainActivity;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;

//유저 유형(프로슈머/컨슈머) 선택, 유형정보를 DB에 저장
public class InitialTypeActivity extends AppCompatActivity {

    ImageView prosumer, consumer;
    Button typeCheck;
    int type = 0;   // 프로슈머(1)/소비자(2) 구분

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_type);

        prosumer = (ImageView)findViewById(R.id.img_prosumer);
        consumer = (ImageView)findViewById(R.id.img_consumer);
        typeCheck = (Button) findViewById(R.id.type_check_button);

        click();
    }

    private void click(){

        prosumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosumer.setImageResource(R.drawable.prosumer3);
                consumer.setImageResource(R.drawable.consumer2);
                type = 1;
            }
        });

        consumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumer.setImageResource(R.drawable.consumer3);
                prosumer.setImageResource(R.drawable.prosumer2);
                type = 2;
            }
        });

        typeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type == 1){
                    GetDB.mUserRef.child(GetAuth.getUserId()).child("type").setValue("prosumer");
                    Toast.makeText(getApplicationContext(), "환영합니다 프로슈머님!", Toast.LENGTH_SHORT).show();
                    goMain();
                }
                    else if(type == 2){
                    GetDB.mUserRef.child(GetAuth.getUserId()).child("type").setValue("consumer");
                    Toast.makeText(getApplicationContext(), "환영합니다 소비자님!", Toast.LENGTH_SHORT).show();
                    goMain();
                }
                else{
                    Toast.makeText(getApplicationContext(), "선택해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goMain(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
