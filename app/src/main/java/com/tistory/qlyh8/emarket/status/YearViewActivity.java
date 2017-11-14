package com.tistory.qlyh8.emarket.status;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tistory.qlyh8.emarket.InfoActivity;
import com.tistory.qlyh8.emarket.MainActivity;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.adapter.TabAdapter;

public class YearViewActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter pagerAdapter;

    private ImageView yearNext;
    private ImageView yearPrev;
    private TextView yearText;
    private ImageView prevBtn;

    private int dataYear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_year_view);

        //Year Data
        dataYear = 2017;
        yearText = (TextView)findViewById(R.id.year_text_view);
        yearText.setText(String.valueOf(dataYear));
        yearNext = (ImageView)findViewById(R.id.year_next_btn);
        yearPrev = (ImageView)findViewById(R.id.year_prev_btn);
        prevBtn = (ImageView)findViewById(R.id.status_year_view_prev);

        yearPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataYearPrevCheck();
                pagerRefresh();
            }
        });
        yearNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataYearNextCheck();
                pagerRefresh();
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome(view);
            }
        });

        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager = (ViewPager)findViewById(R.id.view_pager);

        // Creating TabPagerAdapter adapter
        pagerAdapter = new TabAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), dataYear);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager,true);

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void yearTextSet(){
        yearText.setText(String.valueOf(dataYear));
    }
    private void dataYearPrevCheck(){
        if(dataYear>2016){
            dataYear--;
        }
    }
    private void dataYearNextCheck(){
        if(dataYear<2017){
            dataYear++;
        }
    }
    private void pagerRefresh(){
        yearTextSet();
        pagerAdapter = new TabAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), dataYear);
        viewPager.setAdapter(pagerAdapter);
    }

    public void goHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
