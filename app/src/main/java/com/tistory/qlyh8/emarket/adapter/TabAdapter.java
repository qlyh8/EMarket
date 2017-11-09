package com.tistory.qlyh8.emarket.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.firebase.GetType;
import com.tistory.qlyh8.emarket.main.Status1Activity;
import com.tistory.qlyh8.emarket.main.Status2Activity;

public class TabAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private int year;

    public TabAdapter(FragmentManager fm, int tabCount, int year) {
        super(fm);
        this.tabCount = tabCount;
        this.year = year;
    }

    @Override
    public Fragment getItem(int position) {
        // Returning the current tabs
        Bundle bundle = new Bundle(1);
        bundle.putInt("year", year);

        switch (position) {
            case 0:
                Status1Activity tabFragment1 = new Status1Activity();
                tabFragment1.setArguments(bundle);
                return tabFragment1;
            case 1:
                Status2Activity tabFragment2 = new Status2Activity();
                tabFragment2.setArguments(bundle);
                return tabFragment2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
