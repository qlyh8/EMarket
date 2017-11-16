package com.tistory.qlyh8.emarket.usePattern;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class UsePatternTabAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private String patternType;

    public UsePatternTabAdapter(FragmentManager fm, int tabCount, String patternType) {
        super(fm);
        this.tabCount = tabCount;
        this.patternType = patternType;
    }

    @Override
    public Fragment getItem(int position) {
        // Returning the current tabs
        Bundle bundle = new Bundle(1);
        bundle.putString("patternType", patternType);

        switch (position) {
            case 0:
                UsePattern1Activity tabFragment1 = new UsePattern1Activity();
                tabFragment1.setArguments(bundle);
                return tabFragment1;
            case 1:
                UsePattern2Activity tabFragment2 = new UsePattern2Activity();
                tabFragment2.setArguments(bundle);
                return tabFragment2;
            case 2:
                UsePattern3Activity tabFragment3 = new UsePattern3Activity();
                tabFragment3.setArguments(bundle);
                return tabFragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
