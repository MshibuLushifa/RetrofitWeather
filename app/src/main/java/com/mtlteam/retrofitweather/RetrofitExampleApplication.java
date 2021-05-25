package com.mtlteam.retrofitweather;

import android.app.Application;

import com.squareup.picasso.BuildConfig;

import timber.log.Timber;

public class RetrofitExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
