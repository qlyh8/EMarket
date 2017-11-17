package com.tistory.qlyh8.emarket.usePattern;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tistory.qlyh8.emarket.firebase.GetType;
import com.tistory.qlyh8.emarket.firebase.GetUserDB;

import java.util.Calendar;

//자가진단 탭 어댑터
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
        //GetUserDB.getThisUserDB();

        Bundle bundle = new Bundle(1);

        Calendar calendar = Calendar.getInstance();
        int dataMonth = calendar.get(Calendar.MONTH);

        bundle.putInt("dataMonth", dataMonth);

        switch (position) {
            case 0:
                if(GetType.userType.equals("prosumer")){
                    UsePatternProsumer1Activity tabFragment1 = new UsePatternProsumer1Activity();
                    tabFragment1.setArguments(bundle);
                    return tabFragment1;
                }
                else{
                    UsePatternConsumer1Activity tabFragment1 = new UsePatternConsumer1Activity();
                    tabFragment1.setArguments(bundle);
                    return tabFragment1;
                }

            case 1:
                if(GetType.userType.equals("prosumer")) {
                    if(GetUserDB.thisUserDB.powerProvide > 200){
                        UsePatternProsumer1Activity tabFragment2 = new UsePatternProsumer1Activity();
                        tabFragment2.setArguments(bundle);
                        return tabFragment2;
                    }
                    else{
                        UsePatternProsumer2Activity tabFragment2 = new UsePatternProsumer2Activity();
                        tabFragment2.setArguments(bundle);
                        return tabFragment2;
                    }
                }
                else{
                    if(GetUserDB.thisUserDB.powerUse <= 400){
                        UsePatternConsumer1Activity tabFragment2 = new UsePatternConsumer1Activity();
                        tabFragment2.setArguments(bundle);
                        return tabFragment2;
                    }
                    else{
                        UsePatternConsumer2Activity tabFragment2 = new UsePatternConsumer2Activity();
                        tabFragment2.setArguments(bundle);
                        return tabFragment2;
                    }
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
