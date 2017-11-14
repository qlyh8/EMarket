package com.tistory.qlyh8.emarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.model.User;

public class ProfileActivity extends AppCompatActivity {

    private ImageView goMainBtn;
    private ImageView typeImg;
    private TextView typeText, phoneText, addressText, powerNumberText;

    private User userData = null;

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
        goMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome(view);
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // User is signed in
            GetDB.mUserRef.child(GetAuth.getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userData = dataSnapshot.getValue(User.class);

                    if(userData.getType().equals("prosumer")){
                        typeImg.setImageResource(R.drawable.prosumer1);
                        typeText.setText("PROSUMER");
                    }
                    else if(userData.getType().equals("consumer")){
                        typeImg.setImageResource(R.drawable.consumer1);
                        typeText.setText("CONSUMER");
                    }
                    else {
                        typeImg.setImageResource(R.drawable.sun_128_white);
                        typeText.setText("TYPE");
                    }
                    phoneText.setText(userData.getPhone());
                    addressText.setText(userData.getAddress());
                    powerNumberText.setText(userData.getPowerNumber());
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
    }

    public void goHome(View v){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
