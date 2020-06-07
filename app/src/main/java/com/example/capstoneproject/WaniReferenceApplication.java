package com.example.capstoneproject;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.capstoneproject.di.AppContainer;
import com.example.capstoneproject.utils.MobileAdsConfiguration;

public class WaniReferenceApplication extends Application {

    private AppContainer appContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer(getApplicationContext());
        new MobileAdsConfiguration().init();
    }

    @NonNull
    public final AppContainer getAppContainer() {
        return appContainer;
    }
}