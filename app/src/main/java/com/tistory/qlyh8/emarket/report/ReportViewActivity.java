package com.tistory.qlyh8.emarket.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tistory.qlyh8.emarket.MainActivity;
import com.tistory.qlyh8.emarket.R;

import java.util.Calendar;

//리포트 큰틀
public class ReportViewActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ReportTabAdapter pagerAdapter;

    private ImageView goMainBtn;
    private TextView viewTypeText;
    private int dataMonth;    //전 월

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_view);

        goMainBtn = (ImageView)findViewById(R.id.report_view_prev);
        goMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome(view);
            }
        });

        Calendar calendar = Calendar.getInstance();
        dataMonth = calendar.get(Calendar.MONTH);

        viewTypeText = (TextView)findViewById(R.id.report_view_text);
        viewTypeText.setText(dataMonth + "월 청구서");

        tabLayout = (TabLayout)findViewById(R.id.report_tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        //tabLayout.addTab(tabLayout.newTab());

        viewPager = (ViewPager)findViewById(R.id.report_view_pager);

        // Creating TabPagerAdapter adapter
        pagerAdapter = new ReportTabAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), dataMonth);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager, true);

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    viewTypeText.setText(dataMonth + "월 청구서");
                }
                if(tab.getPosition() == 1){
                    viewTypeText.setText("거래 이력");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    /*private void yearTextSet(){
        viewTypeText.setText(String.valueOf(viewTypeData));
    }*/

    /*private void pagerRefresh(){
        yearTextSet();
        pagerAdapter = new ReportTabAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), viewTypeData);
        viewPager.setAdapter(pagerAdapter);
    }*/

    public void goHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
