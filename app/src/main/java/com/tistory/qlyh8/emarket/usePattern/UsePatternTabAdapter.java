package com.tistory.qlyh8.emarket.usePattern;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tistory.qlyh8.emarket.firebase.GetType;

import java.util.Calendar;

public class UsePatternTabAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private int dataMonth;

    public UsePatternTabAdapter(FragmentManager fm, int tabCount, int dataMonth) {
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

        bundle.putInt("dataMonth", dataMonth);
        switch (position) {
            case 0:
                if(GetType.userType.equals("prosumer")){
                    UsePattern3Activity tabFragment1 = new UsePattern3Activity();
                    tabFragment1.setArguments(bundle);
                    return tabFragment1;
                }
                else{
                    UsePattern1Activity tabFragment1 = new UsePattern1Activity();
                    tabFragment1.setArguments(bundle);
                    return tabFragment1;
                }

            case 1:
                if(GetType.userType.equals("prosumer")) {
                    UsePattern2Activity tabFragment2 = new UsePattern2Activity();
                    tabFragment2.setArguments(bundle);
                    return tabFragment2;
                }
                else{
                    UsePattern2Activity tabFragment2 = new UsePattern2Activity();
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
