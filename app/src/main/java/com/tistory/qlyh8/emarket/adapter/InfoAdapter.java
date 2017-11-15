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

import com.tistory.qlyh8.emarket.LoginActivity;
import com.tistory.qlyh8.emarket.MainActivity;
import com.tistory.qlyh8.emarket.initialize.PhoneNumberAuthentication;
import com.tistory.qlyh8.emarket.R;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;


public class InfoAdapter extends PagerAdapter {

    private Context context;
    //private List<Integer> res;

    Button infoBtn;
    ImageView infoItem2, infoItem3_1, infoItem3_2;
    LovelyInfoDialog infoDialog;

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
        infoDialog = new LovelyInfoDialog(view.getContext());

        if(position == 1) {
            infoItem2 = view.findViewById(R.id.info_item2_btn);
            infoItem2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    infoDialog.setTopColorRes(R.color.colorPrimaryDark)
                            .setIcon(R.drawable.info_dialog)
                            .setTitle("잉여전력")
                            .setMessage("태양광 발전량 - 실제 전기 사용량")
                            .setConfirmButtonText("확인")
                            .show();
                }
            });
        }

        if(position == 2) {
            infoItem3_1 = view.findViewById(R.id.info_item3_btn1);
            infoItem3_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    infoDialog.setTopColorRes(R.color.colorPrimaryDark)
                            .setIcon(R.drawable.info_dialog)
                            .setTitle("거래 추천 대상")
                            .setMessage("전력 거래 시\n이득을 볼 수 있는 컨슈머 조건")
                            .setConfirmButtonText("확인")
                            .show();
                }
            });
            infoItem3_2 = view.findViewById(R.id.info_item3_btn2);
            infoItem3_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    infoDialog.setTopColorRes(R.color.colorPrimaryDark)
                            .setIcon(R.drawable.info_dialog)
                            .setTitle("누진 구간")
                            .setMessage("200KWh 이하\n기본요금\u00A0\u00A0\u00A0 (910원)\n전력량요금 (93.3원/KWh)" +
                                    "\n201~400KWh\n기본요금\u00A0\u00A0\u00A0 (1,600원)\n전력량요금 (187.9원/KWh)" +
                                    "\n400KWh 초과\n기본요금\u00A0\u00A0\u00A0 (7,300원)\n전력량요금 (280.6원/KWh)")
                            .setConfirmButtonText("확인")
                            .show();
                }
            });
        }

        if(position == 3) {
            infoBtn = view.findViewById(R.id.info_btn);
            infoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //context.startActivity(new Intent(context, PhoneNumberAuthentication.class));
                    context.startActivity(new Intent(context, LoginActivity.class));
                    ((Activity)context).finish();
                }
            });
        }

        container.addView(view, 0);
        return view;
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
