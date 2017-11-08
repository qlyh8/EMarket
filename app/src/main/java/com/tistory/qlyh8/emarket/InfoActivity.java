package com.tistory.qlyh8.emarket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tistory.qlyh8.emarket.adapter.InfoAdapter;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Integer> res;
    private InfoAdapter pagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        init();
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
}