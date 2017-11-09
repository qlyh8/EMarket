package com.tistory.qlyh8.emarket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tistory.qlyh8.emarket.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ListAdapter listAdapter;
    private List<Integer> res;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
    }

    private void init(){
        recyclerView = (RecyclerView)findViewById(R.id.recycler_list);
        linearLayoutManager = new LinearLayoutManager(this);
        res = new ArrayList<>();

        res.add(0);
        res.add(0);
        res.add(0);
        res.add(0);

        listAdapter = new ListAdapter(this,res);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listAdapter);
    }
}
