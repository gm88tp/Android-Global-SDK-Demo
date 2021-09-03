package com.immortals.tw;

import android.app.Application;
import android.content.Context;

import com.facebook.ads.AudienceNetworkAds;
import com.global.sdk.GMSDK;
import com.google.firebase.FirebaseApp;

import androidx.multidex.MultiDex;


/**
 * Created by LioN on 2018/11/27.
 * Func：
 */
public class OverSeaApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        GMSDK.initApplication(this);
        FirebaseApp.initializeApp(this);
        AudienceNetworkAds.initialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
