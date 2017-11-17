package com.tistory.qlyh8.emarket.report;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Calendar;

public class ReportTabAdapter  {
//public class ReportTabAdapter extends FragmentStatePagerAdapter {

    /*private int tabCount;
    private int dataMonth;

    public ReportTabAdapter(FragmentManager fm, int tabCount, int dataMonth) {
        super(fm);
        this.tabCount = tabCount;
        this.dataMonth = dataMonth;
    }

    @Override
    public Fragment getItem(int position) {
        // Returning the current tabs
        Bundle bundle = new Bundle(1);
        Calendar calendar = Calendar.getInstance();
        int dataMonth = calendar.get(Calendar.MONTH);

        switch (position) {
            case 0:
                UsePattern1Activity tabFragment1 = new UsePattern1Activity();
                bundle.putInt("dataMonth", dataMonth);
                tabFragment1.setArguments(bundle);
                return tabFragment1;
            case 1:
                UsePattern2Activity tabFragment2 = new UsePattern2Activity();
                bundle.putInt("dataMonth", dataMonth);
                tabFragment2.setArguments(bundle);
                return tabFragment2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }*/
}
