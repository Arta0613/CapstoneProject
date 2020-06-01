package com.example.capstoneproject.data.response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressWarnings("unused")
public class WaniKaniSubjectDataContextSentenceApiResponse {

    @Nullable private final String en;
    @Nullable private final String ja;

    public WaniKaniSubjectDataContextSentenceApiResponse(
            @Nullable final String en, @Nullable final String ja
    ) {
        this.en = en;
        this.ja = ja;
    }

    @NonNull
    @Override
    public String toString() {
        return "WaniKaniSubjectDataContextSentenceApiResponse{" +
                "en='" + en + '\'' +
                ", ja='" + ja + '\'' +
                '}';
    }
}