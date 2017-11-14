package com.tistory.qlyh8.emarket.initialize;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.MainActivity;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;

import java.util.Arrays;

public class PhoneNumberAuthentication extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            // already signed in
            startActivity(new Intent(PhoneNumberAuthentication.this, MainActivity.class));
            finish();
        } else {
            // not signed in
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(
                                    Arrays.asList(
                                            new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()
                                    ))
                            .setTheme(R.style.RedTheme)
                            .build(),
                    RC_SIGN_IN);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            //Log.d("requestCode", ""+requestCode);
            IdpResponse response = IdpResponse.fromResultIntent(data);
            // Successfully signed in
            if (resultCode == ResultCodes.OK) {
                //Log.d("resultCode", ""+resultCode);
                userRegister();
                return;
            }
            else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Log.e("Login","Login canceled by User");
                    Toast.makeText(getApplicationContext(), "로그인이 취소되었습니다", Toast.LENGTH_LONG).show();
                    return;
                }
                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Log.e("Login","No Internet Connection");
                    Toast.makeText(getApplicationContext(), "인터넷 연결이 끊어졌습니다", Toast.LENGTH_LONG).show();
                    return;
                }
                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Log.e("Login","Unknown Error");
                    Toast.makeText(getApplicationContext(), "알 수 없는 오류입니다", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            Log.e("Login","Unknown sign in response");
        }
    }

    private void userRegister(){

        GetDB.mUserRef.addValueEventListener(new ValueEventListener() {
            boolean isUser = false;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    if(snapshot.getKey().equals(GetAuth.getUserId())) {
                        isUser = true;
                        break;
                    }
                    //System.out.println(snapshot.child("title").getValue(String.class));
                }

                if(!isUser){
                    GetDB.mUserRef.child(GetAuth.getUserId()).child("phone").setValue(GetAuth.getUserPhone());
                    //Toast.makeText(getApplicationContext(), "완료되었습니다", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(getBaseContext(), InfoActivity.class));
                    //finish();
                }
                else{
                    //startActivity(new Intent(getBaseContext(), MainActivity.class));
                    //finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        startActivity(new Intent(getBaseContext(), InitialPowerNumberActivity.class));
        finish();
    }
}
