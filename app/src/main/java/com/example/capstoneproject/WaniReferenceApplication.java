package com.example.capstoneproject;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.capstoneproject.di.AppContainer;

public class WaniReferenceApplication extends Application {

    private AppContainer appContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer(getApplicationContext());
    }

    @NonNull
    public final AppContainer getAppContainer() {
        return appContainer;
    }
}