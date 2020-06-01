package com.example.capstoneproject.data.response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class WaniKaniSubjectsPagesApiResponse {

    @SerializedName("per_page")
    @Nullable private final Integer perPage;

    @SerializedName("next_url")
    @Nullable private final String nextUrl;

    @SerializedName("previous_url")
    @Nullable private final String previousUrl;

    public WaniKaniSubjectsPagesApiResponse(
            @Nullable final Integer perPage,
            @Nullable final String nextUrl,
            @Nullable final String previousUrl
    ) {
        this.perPage = perPage;
        this.nextUrl = nextUrl;
        this.previousUrl = previousUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return "WaniKaniSubjectsPagesApiResponse{" +
                "perPage='" + perPage + '\'' +
                ", nextUrl='" + nextUrl + '\'' +
                ", previousUrl='" + previousUrl + '\'' +
                '}';
    }
}