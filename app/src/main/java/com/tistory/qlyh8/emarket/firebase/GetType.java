package com.tistory.qlyh8.emarket.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class GetType {

    public static String userType = "";

    public void isType(){

        GetDB.mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    if(snapshot.getKey().equals(GetAuth.getGoogleUserId())) {
                        userType = snapshot.child("type").getValue(String.class);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }
}
