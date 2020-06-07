package com.example.capstoneproject.utils;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class ShareTask extends AsyncTask<String, Void, String> {

    @NonNull
    private final WeakReference<Context> contextWeakReference;

    public ShareTask(@NonNull final WeakReference<Context> contextWeakReference) {
        this.contextWeakReference = contextWeakReference;
    }

    @Override
    protected String doInBackground(@NonNull final String... strings) {
        return strings[0];
    }

    @Override
    protected void onPostExecute(@NonNull final String shareText) {
        final Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        sendIntent.setType("text/plain");

        final Intent shareIntent = Intent.createChooser(sendIntent, shareText);
        contextWeakReference.get().startActivity(shareIntent);
    }
}