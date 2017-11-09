package com.tistory.qlyh8.emarket.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.firebase.GetType;

public class Status1Activity extends Fragment {

    private int year;
    private int currentYear = 2017;
    View view;
    private TextView enrollYet;
    private Button enrollBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        year = getArguments().getInt("year");

        view = inflater.inflate(R.layout.activity_status1, container, false);
        enrollYet = view.findViewById(R.id.enroll_yet_text);
        enrollBtn = view.findViewById(R.id.enroll_button);

        isEnroll();

        return view;
    }

    public void isEnroll(){

        GetDB.mEnrollRef.child(GetType.userType).addValueEventListener(new ValueEventListener() {
            boolean isEnroll = false;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    if(snapshot.getKey().equals(GetAuth.getGoogleUserId())) {
                        for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                            if(childSnapshot.child("year").equals(year)) {
                                isEnroll = true;
                                enrollYet.setVisibility(view.INVISIBLE);
                                enrollBtn.setVisibility(view.INVISIBLE);
                                break;
                            }
                        }
                    }
                }

                if(!isEnroll){
                    enrollYet.setVisibility(view.VISIBLE);
                    if(year == currentYear){
                        enrollBtn.setVisibility(view.VISIBLE);
                    }
                    else{
                        enrollBtn.setVisibility(view.INVISIBLE);
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
