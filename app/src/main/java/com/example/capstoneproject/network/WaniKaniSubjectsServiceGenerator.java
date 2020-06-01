package com.example.capstoneproject.network;

import androidx.annotation.NonNull;

import com.example.capstoneproject.BuildConfig;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WaniKaniSubjectsServiceGenerator {

    private static final String BASE_URL = "https://api.wanikani.com/v2/";

    @NonNull
    public static Retrofit.Builder buildWaniKaniSubjectsServiceGenerator() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(InterceptorClient.createInterceptor());
    }
}

class InterceptorClient {

    private static final String BEARER = "Bearer ";
    private static final String WANIKANI_REVISION = "Wanikani-Revision";
    private static final String WANIKANI_REVISION_DATE = "20170710";

    @NonNull
    public static OkHttpClient createInterceptor() {

        final Interceptor interceptor = chain -> {
            final Request original = chain.request();

            final Request.Builder requestBuilder = original.newBuilder()
                    .header(BuildConfig.AUTHORIZATION, BEARER + BuildConfig.TOKEN)
                    .addHeader(WANIKANI_REVISION, WANIKANI_REVISION_DATE)
                    .method(original.method(), original.body());

            final Request request = requestBuilder.build();

            return chain.proceed(request);
        };

        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);

        return builder.build();
    }
}