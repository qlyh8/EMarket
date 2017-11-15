package com.tistory.qlyh8.emarket.firebase;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GetDB {

    public static FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    public static DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference();
    public static DatabaseReference mUserRef = mDatabaseReference.child("user");
    public static DatabaseReference mEnrollRef = mDatabaseReference.child("enroll");
    public static DatabaseReference mTradeRef = mDatabaseReference.child("trade");
}
