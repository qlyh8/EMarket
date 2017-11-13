package com.tistory.qlyh8.emarket;

import android.content.Intent;
import android.os.Handler;

import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashActivity extends AwesomeSplash {

    private final int duration = 2000;
    private final int DELAY = 2000;

    @Override
    public void initSplash(ConfigSplash configSplash) {

        configSplash.setBackgroundColor(R.color.colorPrimaryDark);
        configSplash.setAnimCircularRevealDuration(duration);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

        configSplash.setLogoSplash(R.drawable.logo);
        configSplash.setAnimLogoSplashDuration(duration);

        configSplash.setTitleSplash("내가 직접 거래하는 전기시장");
        configSplash.setTitleTextSize(15);
        configSplash.setAnimTitleDuration(1000);
        //configSplash.setTitleFont("fonts/To The Point.ttf");
    }

    @Override
    public void animationsFinished() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY);
    }
}
