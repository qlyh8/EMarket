package com.tistory.qlyh8.emarket.report;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tistory.qlyh8.emarket.R;

import java.util.List;

//리포트 컨슈머 거래이력 월별 리스트
public class ReportConsumerItem extends RecyclerView.Adapter<ReportConsumerItem.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<String> res;

    private List<Integer> resMoney1, resMoney2, resMoney3;

    public ReportConsumerItem(Context context, List<String> res, List<Integer> resMoney1, List<Integer> resMoney2, List<Integer> resMoney3){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.res = res;
        this.resMoney1 = resMoney1;
        this.resMoney2 = resMoney2;
        this.resMoney3 = resMoney3;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.report_item_consumer2_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.month.setText(String.valueOf(position+1)+"월");
        holder.before.setText(String.valueOf(position+1000));
        holder.after.setText(String.valueOf(position+600));
        holder.save.setText(String.valueOf((position+1000) - (position+600)));
    }

    @Override
    public int getItemCount() {
        return res.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView month;
        private TextView before, after, save;

        public ViewHolder(View itemView) {
            super(itemView);
            month = itemView.findViewById(R.id.month);
            before = itemView.findViewById(R.id.report_consumer_list_before);
            after = itemView.findViewById(R.id.report_consumer_list_after);
            save = itemView.findViewById(R.id.report_consumer_list_save);
        }
    }
}
