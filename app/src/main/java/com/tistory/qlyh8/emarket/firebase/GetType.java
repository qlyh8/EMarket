package com.tistory.qlyh8.emarket.firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class GetType {

    public static String userType = "";

    public static void isType(){

        GetDB.mUserRef.child(GetAuth.getGoogleUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userType = dataSnapshot.child("type").getValue(String.class);
                Log.d("qwe",userType);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }
}
