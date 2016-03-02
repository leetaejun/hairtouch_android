package kr.co.lnkbeauty.hairtouch_android.application;

import android.app.Application;

import kr.co.lnkbeauty.hairtouch_android.util.PreferencesManager;


/**
 * Created by leetaejun on 2016. 2. 14..
 */
public class HTApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        PreferencesManager.initializeInstance(this);
    }
}
