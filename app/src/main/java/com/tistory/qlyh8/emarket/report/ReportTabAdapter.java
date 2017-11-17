package com.tistory.qlyh8.emarket.report;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tistory.qlyh8.emarket.firebase.GetType;
import com.tistory.qlyh8.emarket.firebase.GetUserDB;
import com.tistory.qlyh8.emarket.usePattern.UsePatternConsumer1Activity;
import com.tistory.qlyh8.emarket.usePattern.UsePatternConsumer2Activity;
import com.tistory.qlyh8.emarket.usePattern.UsePatternProsumer1Activity;
import com.tistory.qlyh8.emarket.usePattern.UsePatternProsumer2Activity;

import java.util.Calendar;

//리포트 어댑터
public class ReportTabAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private int dataMonth;

    public ReportTabAdapter(FragmentManager fm, int tabCount, int dataMonth) {
        super(fm);
        this.tabCount = tabCount;
        this.dataMonth = dataMonth;
    }

    @Override
    public Fragment getItem(int position) {
        // Returning the current tabs
        //GetUserDB.getThisUserDB();

        Bundle bundle = new Bundle(1);

        Calendar calendar = Calendar.getInstance();
        int dataMonth = calendar.get(Calendar.MONTH);

        bundle.putInt("dataMonth", dataMonth);

        switch (position) {
            case 0:
                if(GetType.userType.equals("prosumer")){
                    ReportProsumer1Activity tabFragment1 = new ReportProsumer1Activity();
                    tabFragment1.setArguments(bundle);
                    return tabFragment1;
                }
                else{
                    ReportConsumer1Activity tabFragment1 = new ReportConsumer1Activity();
                    tabFragment1.setArguments(bundle);
                    return tabFragment1;
                }

            case 1:
                if(GetType.userType.equals("prosumer")) {
                    ReportProsumer2Activity tabFragment2 = new ReportProsumer2Activity();
                    tabFragment2.setArguments(bundle);
                    return tabFragment2;
                }
                else{
                    ReportConsumer2Activity tabFragment2 = new ReportConsumer2Activity();
                    tabFragment2.setArguments(bundle);
                    return tabFragment2;
                }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
