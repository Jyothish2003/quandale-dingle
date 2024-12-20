package com.jk.simplewallpaper;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;


import com.jk.simplewallpaper.activities.SplashActivity;
import com.jk.simplewallpaper.data_source.DataService;
import com.jk.simplewallpaper.data_source.SQLCategories;
import com.jk.simplewallpaper.data_source.impl.SQLDataServiceImpl;

import com.jk.simplewallpaper.utils.SQLFav;
import com.jk.simplewallpaper.utils.Utils;


public class MainApplication extends android.app.Application
        implements ActivityLifecycleCallbacks, DefaultLifecycleObserver {


    private Activity currentActivity;

    private static final String TAG = "MyApplication";

    private int interstitialAfterClicks;
    private int cardClicks;
    private DataService dataService;

    public static DataService getDataService(Application application) {
        return ((MainApplication) application).dataService;
    }

    public void interstitialShown() {
        Log.d(TAG, "interstitialShown: called");
        cardClicks = 0;
    }

    public boolean canShowInterstitial() {
        cardClicks++;
        Log.d(TAG, "canShowInterstitial: " + cardClicks);
        return cardClicks >= interstitialAfterClicks;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.registerActivityLifecycleCallbacks(this);

        interstitialAfterClicks = getResources().getInteger(R.integer.interstitial_after_clicks);
        cardClicks = interstitialAfterClicks - 2;
        Utils.initTheme(this);

        SQLCategories sqlCategories = new SQLCategories(this);
        SQLFav sqlFav = new SQLFav(this);
        dataService = new SQLDataServiceImpl(this, sqlCategories, sqlFav);



        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {

    }



    /**
     * ActivityLifecycleCallback methods.
     */
    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
    }

}
