package com.tistory.qlyh8.emarket;

import android.content.Intent;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tistory.qlyh8.emarket.info.InfoActivity;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

//첫 애니메이션 화면
public class SplashActivity extends AwesomeSplash {

    private final int duration = 1200;
    private final int DELAY = 1200;

    @Override
    public void initSplash(ConfigSplash configSplash) {

        configSplash.setBackgroundColor(R.color.colorPrimaryDark);
        configSplash.setAnimCircularRevealDuration(duration);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

        configSplash.setLogoSplash(R.drawable.logo_128);
        configSplash.setAnimLogoSplashDuration(duration);

        configSplash.setTitleSplash("내가 직접 거래하는 전기시장");
        configSplash.setTitleTextSize(15);
        configSplash.setAnimTitleDuration(1300);
        //configSplash.setTitleFont("fonts/To The Point.ttf");
    }

    @Override
    public void animationsFinished() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    //startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                    finish();
                }
                else{
                    startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                    finish();
                }
            }
        }, DELAY);
    }
}
