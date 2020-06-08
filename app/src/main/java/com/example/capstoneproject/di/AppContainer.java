package com.example.capstoneproject.di;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.capstoneproject.repository.WaniRepository;

public class AppContainer {

    @NonNull private final WaniRepository waniRepository;

    public AppContainer(@NonNull final Context context) {
        waniRepository = new WaniRepository(context);
    }

    @NonNull
    public final WaniRepository getWaniRepository() {
        return waniRepository;
    }
}