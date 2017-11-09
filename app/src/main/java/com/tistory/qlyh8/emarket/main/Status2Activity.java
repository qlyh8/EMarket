package com.tistory.qlyh8.emarket.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tistory.qlyh8.emarket.R;

public class Status2Activity extends Fragment {

    private int year;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_status2, container, false);
        year = getArguments().getInt("year");

        return view;
    }
}
