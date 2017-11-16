package com.tistory.qlyh8.emarket.trade;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tistory.qlyh8.emarket.R;

import java.util.List;

//프로슈머 리스트 어댑터
public class TradeListAdapter1 extends RecyclerView.Adapter<TradeListAdapter1.ViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private List<String> res1, res2;

    public TradeListAdapter1(Context context, List<String> res1,  List<String> res2){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.res1 = res1;
        this.res2 = res2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.trade_list_item1, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //각 아이템의 이벤트를 할당
        holder.nameTextView.setText(res1.get(position));
        holder.powerTextView.setText(res2.get(position));
    }

    @Override
    public int getItemCount() {
        return res1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //각 아이템의 항목을 할당
        private TextView nameTextView, powerTextView;

        public ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.trade_list_item1_name_text);
            powerTextView = view.findViewById(R.id.trade_list_item1_power_text);
        }
    }
}
