package com.example.capstoneproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.capstoneproject.R;

import java.util.Objects;


public class NetworkState {

    public static boolean isConnectedToNetwork(@NonNull final Context context) {
        try {
            final ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            final NetworkInfo activeNetwork = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();

            return activeNetwork != null && activeNetwork.isConnected();
        } catch (NullPointerException e) {
            Log.e(NetworkState.class.getSimpleName(), context.getString(R.string.error_retrieving_connection), e);
            return false;
        }
    }
}