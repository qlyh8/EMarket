package com.tistory.qlyh8.emarket.notUsed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.tistory.qlyh8.emarket.R;

import java.util.List;

//샘플 리스트 어댑터
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private List<Integer> res;

    public ListAdapter(Context context, List<Integer> res){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.res = res;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //각 아이템의 이벤트를 할당
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mInflater.getContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return res.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //각 아이템의 항목을 할당
        private Button button;

        public ViewHolder(View view) {
            super(view);
            button = view.findViewById(R.id.list_button);
        }
    }
}
