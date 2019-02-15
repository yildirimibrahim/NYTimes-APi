package com.softtech.nytime;

import android.app.Application;
import android.content.Context;
import timber.log.Timber;

public class NYTimesApp
        extends Application {

    /**
     * app level shared context without memory leak problem
     */
    private static NYTimesApp instance;

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.i("onCreate");
        instance = this;
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
    }

}