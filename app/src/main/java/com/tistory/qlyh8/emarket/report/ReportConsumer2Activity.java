package com.tistory.qlyh8.emarket.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tistory.qlyh8.emarket.R;

//리포트 프로슈머 거래이력
public class ReportConsumer2Activity extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.report_item_consumer2, container, false);

        return view;
    }
}
