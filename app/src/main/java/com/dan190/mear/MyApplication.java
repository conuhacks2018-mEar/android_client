package com.dan190.mear;

import android.app.Application;

import com.dan190.mear.services.MyNotification;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by Dan on 27/01/2018.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // TODO Plant Crashlytics tree
        }

        // Create notification channel
        MyNotification.INSTANCE.createNotificationChannel(this,
                getString(R.string.channel_name),
                getString(R.string.channel_description));

        Realm.init(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
