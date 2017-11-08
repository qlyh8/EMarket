package com.tistory.qlyh8.emarket.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tistory.qlyh8.emarket.R;

import java.util.List;


public class InfoAdapter extends PagerAdapter {

    private Context context;
    //private List<Integer> res;

    ImageView prosumer, consumer;
    Button typeCheck;
    int type = 0;   // 프로슈머(1)/소비자(2) 구분

/*
    public InfoAdapter(Context context, List<Integer> res) {
        this.context = context;
        this.res = res;
    }*/
    public InfoAdapter(Context context) {
        this.context = context;
    }
/*
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.info_item, container, false);

        ImageView imageView = view.findViewById(R.id.info_image);
        imageView.setImageResource(res.get(position));

        container.addView(view);
        return view;
    }
*/
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Using different layouts in the view pager instead of images.

        int resId = -1;

        switch (position) {
            case 0:
                resId = R.layout.info_item1;
                break;
            case 1:
                resId = R.layout.info_item2;
                break;
            case 2:
                resId = R.layout.info_item3;
                break;
        }

        View view = inflater.inflate(resId, container, false);

        if(position == 2) {
            prosumer = view.findViewById(R.id.img_prosumer);
            consumer = view.findViewById(R.id.img_consumer);
            typeCheck = view.findViewById(R.id.type_check_button);

            click();
        }

        container.addView(view, 0);
        return view;
    }

    private void click(){
        prosumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosumer.setImageResource(R.drawable.prosumer2);
                consumer.setImageResource(R.drawable.consumer1);
                type = 1;
            }
        });

        consumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumer.setImageResource(R.drawable.consumer2);
                prosumer.setImageResource(R.drawable.prosumer1);
                type = 2;
            }
        });

        typeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type == 1){
                    Toast.makeText(context, "나는 프로슈머!", Toast.LENGTH_SHORT).show();
                }
                else if(type == 2){
                    Toast.makeText(context, "나는 소비자!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "선택해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getCount() {
        //return res.size();
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
