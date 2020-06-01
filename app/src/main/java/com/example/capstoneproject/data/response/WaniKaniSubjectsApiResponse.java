package com.example.capstoneproject.data.response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// https://docs.api.wanikani.com/20170710/#subjects
@SuppressWarnings("unused")
public class WaniKaniSubjectsApiResponse {

    @Nullable private final String object;
    @Nullable private final String url;
    @Nullable private final WaniKaniSubjectsPagesApiResponse pages;

    @SerializedName("total_count")
    @Nullable private final Integer totalCount;

    @SerializedName("data_updated_at")
    @Nullable private final String dataUpdatedAt;

    @Nullable private final List<WaniKaniSubjectApiResponse> data;

    public WaniKaniSubjectsApiResponse(
            @Nullable final String object,
            @Nullable final String url,
            @Nullable final WaniKaniSubjectsPagesApiResponse pages,
            @Nullable final Integer totalCount,
            @Nullable final String dataUpdatedAt,
            @Nullable final List<WaniKaniSubjectApiResponse> data
    ) {
        this.object = object;
        this.url = url;
        this.pages = pages;
        this.totalCount = totalCount;
        this.dataUpdatedAt = dataUpdatedAt;
        this.data = data;
    }

    @Nullable
    public final List<WaniKaniSubjectApiResponse> getData() {
        return data;
    }

    @NonNull
    @Override
    public String toString() {
        return "WaniKaniSubjectsApiResponse{\n" +
                "object='" + object + '\'' +
                ",\n url='" + url + '\'' +
                ",\n pages=" + pages +
                ",\n totalCount=" + totalCount +
                ",\n dataUpdatedAt='" + dataUpdatedAt + '\'' +
                ",\n data=" + data +
                "\n}";
    }
}