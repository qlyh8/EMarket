package com.tistory.qlyh8.emarket;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

public class LoadingActivity extends Dialog {
    //별도의 로딩 클래스
    //Loading a = new Loading(액티비티이름.this);
    //a.show(); 나타내기 ,  a.dismiss() 사라지기
    //loading.xml, drawable/loading.xml, activity_loading 같이 사용
    public LoadingActivity(Context context)
    {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setCanceledOnTouchOutside(false);
        setContentView(R.layout.activity_loading);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
