package com.tistory.qlyh8.emarket.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.model.User;

//현재 유저의 DB 가져오기
public class GetUserDB {

    public static User thisUserDB = null;

    public static void getThisUserDB(){

        GetDB.mUserRef.child(GetAuth.getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                thisUserDB = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }
}
