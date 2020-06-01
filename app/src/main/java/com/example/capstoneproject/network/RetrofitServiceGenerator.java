package com.example.capstoneproject.network;

import androidx.annotation.NonNull;

import retrofit2.Retrofit;

public class RetrofitServiceGenerator {

    private final static Retrofit.Builder waniKaniApiBuilder =
            WaniKaniSubjectsServiceGenerator.buildWaniKaniSubjectsServiceGenerator();

    @NonNull
    public static <S> S createSubjectsApiService(@NonNull final Class<S> serviceClass) {
        return waniKaniApiBuilder.build().create(serviceClass);
    }
}