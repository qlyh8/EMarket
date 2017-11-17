package com.tistory.qlyh8.emarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;

//내 정보
public class ProfileActivity extends AppCompatActivity {

    private ImageView goMainBtn;
    private ImageView typeImg;
    private TextView typeText, phoneText, addressText, powerNumberText;

    private LoadingActivity loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        goMainBtn = (ImageView)findViewById(R.id.profile_prev);
        typeImg = (ImageView)findViewById(R.id.profile_img);
        typeText = (TextView)findViewById(R.id.profile_type);
        phoneText = (TextView)findViewById(R.id.profile_phone);
        addressText = (TextView)findViewById(R.id.profile_address);
        powerNumberText = (TextView)findViewById(R.id.profile_power_number);

        initProfile();
    }

    public void initProfile(){

        loading = new LoadingActivity(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // User is signed in
            loading.show();
            GetDB.mUserRef.child(GetAuth.getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(dataSnapshot.child("type").getValue().equals("prosumer")){
                        typeImg.setImageResource(R.drawable.prosumer1);
                        typeText.setText(dataSnapshot.child("username").getValue() + " 프로슈머");
                    }
                    else if(dataSnapshot.child("type").getValue().equals("consumer")){
                        typeImg.setImageResource(R.drawable.consumer1);
                        typeText.setText(dataSnapshot.child("username").getValue() + " 컨슈머");
                    }
                    else {
                        typeImg.setImageResource(R.drawable.sun_128_white);
                        typeText.setText("NAME &amp; TYPE");
                    }
                    phoneText.setText(dataSnapshot.child("phone").getValue().toString());
                    addressText.setText(dataSnapshot.child("address").getValue().toString());
                    powerNumberText.setText(dataSnapshot.child("powerNumber").getValue().toString());
                    loading.dismiss();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    throw databaseError.toException();
                }
            });
        } else {
            // No user is signed in
            typeImg.setImageResource(R.drawable.sun_128_white);
            typeText.setText("로그인 상태가 아닙니다.");
            phoneText.setText("");
            addressText.setText("");
            powerNumberText.setText("");
        }

        goMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome(view);
            }
        });
    }

    public void goHome(View v){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
