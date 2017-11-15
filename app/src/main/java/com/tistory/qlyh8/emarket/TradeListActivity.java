package com.tistory.qlyh8.emarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tistory.qlyh8.emarket.adapter.ListAdapterTrade;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.util.ArrayList;
import java.util.List;

public class TradeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ListAdapterTrade listAdapter;
    private List<Integer> res;

    private ImageView goPrevBtn;
    private TextView infoBtn;
    LovelyInfoDialog infoDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade_list);

        recyclerView = (RecyclerView)findViewById(R.id.trade_recycler_list);
        goPrevBtn = (ImageView)findViewById(R.id.trade_list_prev);
        infoBtn = (TextView)findViewById(R.id.trade_list_info);
        infoDialog = new LovelyInfoDialog(this);

        init();
    }

    private void init(){
        linearLayoutManager = new LinearLayoutManager(this);
        res = new ArrayList<>();

        res.add(0);
        res.add(0);
        res.add(0);
        res.add(0);

        listAdapter = new ListAdapterTrade(this, res);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listAdapter);

        //돌아가기 버튼
        goPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        //거래 안내 다이얼로그
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoDialog.setTopColorRes(R.color.colorPrimaryDark)
                        .setIcon(R.drawable.info_dialog)
                        .setTitle("거래 안내")
                        .setMessage("모든 거래는\n전월 사용량 기준이며"
                                + "\n한국전력공사의 승인 이후에 가능하고"
                                + "\n현금이 아닌 전월 사용량에 대한 전기 요금으로 정산됩니다.")
                        .setConfirmButtonText("확인")
                        .show();
            }
        });
    }
}
