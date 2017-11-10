package com.tistory.qlyh8.emarket.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tistory.qlyh8.emarket.MainActivity;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;


public class InfoAdapter extends PagerAdapter {

    private Context context;
    //private List<Integer> res;

    ImageView prosumer, consumer;
    Button typeCheck;
    int type = 0;   // 프로슈머(1)/소비자(2) 구분
    int year = 2017;    //등록 날짜

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
            case 3:
                resId = R.layout.info_item4;
                break;
        }

        View view = inflater.inflate(resId, container, false);

        if(position == 3) {
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
                    GetDB.mUserRef.child(GetAuth.getGoogleUserId()).child("type").setValue("prosumer");
                    Toast.makeText(context, "환영합니다 "+ GetAuth.getGoogleUserName() +" 프로슈머님!", Toast.LENGTH_SHORT).show();
                    goMain();
                }
                    else if(type == 2){
                    GetDB.mUserRef.child(GetAuth.getGoogleUserId()).child("type").setValue("consumer");
                    Toast.makeText(context, "환영합니다 "+ GetAuth.getGoogleUserName() +" 소비자님!", Toast.LENGTH_SHORT).show();
                    goMain();
                }
                else{
                    Toast.makeText(context, "선택해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goMain(){
        context.startActivity(new Intent(context, MainActivity.class));
        //context.startActivity(new Intent(context, YearViewActivity.class));
        //context.startActivity(new Intent(context, ListActivity.class));
        ((Activity)context).finish();
    }

    @Override
    public int getCount() {
        //return res.size();
        return 4;
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
