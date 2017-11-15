package com.tistory.qlyh8.emarket.status;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.adapter.ListAdapterStatusTrade;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.util.ArrayList;
import java.util.List;

public class TradeStatusActivity extends Fragment {

    private int year;

    View view;

    private TextView infoBtn;
    LovelyInfoDialog infoDialog;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ListAdapterStatusTrade listAdapter;
    private List<Integer> res;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.status_trade, container, false);
        year = getArguments().getInt("year");

        infoBtn = view.findViewById(R.id.status_trade_info);
        infoDialog = new LovelyInfoDialog(getContext());

        init();
        click();

        return view;
    }

    private void init(){
        //한전 신청현황
        recyclerView = view.findViewById(R.id.status_trade_recycler_list);
        linearLayoutManager = new LinearLayoutManager(getContext());

        res = new ArrayList<>();

        res.add(0);
        res.add(0);

        listAdapter = new ListAdapterStatusTrade(getContext(), res);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listAdapter);
    }

    private void click(){
        //과정 안내 다이얼로그
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoDialog.setTopColorRes(R.color.colorPrimaryDark)
                        .setIcon(R.drawable.info_dialog)
                        .setTitle("과정 안내")
                        .setMessage("1. 신청접수\n2. 거래적합 검토\n3. 승인 대기\n4. 승인 완료")
                        .setConfirmButtonText("확인")
                        .show();
            }
        });
    }
}
