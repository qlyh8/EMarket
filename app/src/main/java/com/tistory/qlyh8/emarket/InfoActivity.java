package com.tistory.qlyh8.emarket;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.tistory.qlyh8.emarket.adapter.InfoAdapter;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Integer> res;
    private InfoAdapter pagerAdapter;

    ImageView prosumer, consumer;
    Button typeCheck;
    int type = 0;   // 프로슈머(1)/소비자(2) 구분

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.info_item3, null);
        //View inflatedView = getLayoutInflater().inflate(R.layout.info_item3, null);

        prosumer = view.findViewById(R.id.img_prosumer);
        consumer = view.findViewById(R.id.img_consumer);
        typeCheck = view.findViewById(R.id.type_check_button);
        TextView tt = view.findViewById(R.id.tt);
        tt.setText("aaaa");
        init();
        click();
    }

    private void init(){
        res = new ArrayList<>();

        viewPager = (ViewPager)findViewById(R.id.info_view_pager);
        tabLayout = (TabLayout)findViewById(R.id.info_tab_layout);

        res.add(R.drawable.info1);
        res.add(R.drawable.info2);
        res.add(R.drawable.info1);

        //pagerAdapter = new InfoAdapter(this,res);
        pagerAdapter = new InfoAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager,true);
    }

   private void click(){
        prosumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosumer.setImageResource(R.drawable.prosumer2);
                consumer.setImageResource(R.drawable.consumer1);
                type = 1;
            }
        });

        consumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumer.setImageResource(R.drawable.consumer2);
                prosumer.setImageResource(R.drawable.prosumer1);
                type = 2;
            }
        });

        typeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type == 1){
                    Toast.makeText(getApplicationContext(), "나는 프로슈머!", Toast.LENGTH_SHORT).show();
                }
                else if(type == 2){
                    Toast.makeText(getApplicationContext(), "나는 소비자!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "선택해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
