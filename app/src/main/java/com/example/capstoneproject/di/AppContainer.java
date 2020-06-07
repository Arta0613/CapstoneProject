package com.example.capstoneproject.di;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.capstoneproject.repository.WaniRepository;
import com.google.firebase.analytics.FirebaseAnalytics;

public class AppContainer {

    @NonNull private final WaniRepository waniRepository;
    @NonNull private final FirebaseAnalytics firebaseAnalytics;

    public AppContainer(@NonNull final Context context) {
        waniRepository = new WaniRepository(context);
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    @NonNull
    public final WaniRepository getWaniRepository() {
        return waniRepository;
    }

    @NonNull
    public final FirebaseAnalytics getFirebaseAnalytics() {
        return firebaseAnalytics;
    }
}