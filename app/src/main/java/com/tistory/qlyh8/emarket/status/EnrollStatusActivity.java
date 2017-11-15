package com.tistory.qlyh8.emarket.status;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.adapter.ListAdapterStatusEnroll;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.firebase.GetType;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.util.ArrayList;
import java.util.List;

public class EnrollStatusActivity extends Fragment {

    private int year;
    private int currentYear = 2017;
    View view;
    //private TextView enrollYet;
    private Button enrollBtn;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ListAdapterStatusEnroll listAdapter;
    private List<Integer> res;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        year = getArguments().getInt("year");

        view = inflater.inflate(R.layout.status_enroll, container, false);

        //enrollYet = view.findViewById(R.id.enroll_yet_text);
        enrollBtn = view.findViewById(R.id.enroll_button);

        isEnroll();
        init();
        click();

        return view;
    }

    public void isEnroll(){

        GetDB.mEnrollRef.child(GetType.userType).addValueEventListener(new ValueEventListener() {
            boolean isEnroll = false;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    if(snapshot.getKey().equals(GetAuth.getUserId())) {
                        for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                            if(childSnapshot.child("year").equals(year)) {
                                isEnroll = true;
                                //enrollYet.setVisibility(view.INVISIBLE);
                                enrollBtn.setVisibility(view.INVISIBLE);
                                break;
                            }
                        }
                    }
                }

                if(!isEnroll){
                    //enrollYet.setVisibility(view.VISIBLE);
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

    private void init(){
        //거래 신청현황
        recyclerView = view.findViewById(R.id.status_enroll_recycler_list);
        linearLayoutManager = new LinearLayoutManager(getContext());

        res = new ArrayList<>();

        res.add(0);
        res.add(0);
        res.add(0);

        listAdapter = new ListAdapterStatusEnroll(getContext(), res);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listAdapter);
    }

    private void click(){
        //등록하기
        enrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v = view;
                startActivity(new Intent(v.getContext(), EnrollFormActivity.class));
                //((Activity)v.getContext()).finish();
            }
        });
    }
}
