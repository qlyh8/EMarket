package com.tistory.qlyh8.emarket.usePattern;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tistory.qlyh8.emarket.MainActivity;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.adapter.TabAdapter;
import com.tistory.qlyh8.emarket.adapter.TabAdapterUsePattern;

public class UsePatternViewActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapterUsePattern pagerAdapter;

    private TextView viewTypeText;
    private String viewTypeData;    //소비패턴(일/주/월)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_pattern_view);

        //Year Data
        viewTypeData = "소비패턴";
        viewTypeText = (TextView)findViewById(R.id.use_pattern_view_text);
        viewTypeText.setText(viewTypeData);

        tabLayout = (TabLayout)findViewById(R.id.use_pattern_tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager = (ViewPager)findViewById(R.id.use_pattern_view_pager);

        // Creating TabPagerAdapter adapter
        pagerAdapter = new TabAdapterUsePattern(getSupportFragmentManager(), tabLayout.getTabCount(), viewTypeData);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager, true);

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
        viewTypeText.setText(String.valueOf(viewTypeData));
    }

    private void pagerRefresh(){
        yearTextSet();
        pagerAdapter = new TabAdapterUsePattern(getSupportFragmentManager(), tabLayout.getTabCount(), viewTypeData);
        viewPager.setAdapter(pagerAdapter);
    }

    public void goHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
