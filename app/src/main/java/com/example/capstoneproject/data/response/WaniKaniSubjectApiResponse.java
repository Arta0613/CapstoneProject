package com.example.capstoneproject.data.response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class WaniKaniSubjectApiResponse {

    @Nullable private final Integer id;
    @Nullable private final String object;
    @Nullable private final String url;

    @SerializedName("data_updated_at")
    @Nullable private final String dataUpdatedAt;

    @Nullable private final WaniKaniSubjectDataApiResponse data;

    public WaniKaniSubjectApiResponse(
            @Nullable final Integer id,
            @Nullable final String object,
            @Nullable final String url,
            @Nullable final String dataUpdatedAt,
            @Nullable final WaniKaniSubjectDataApiResponse data
    ) {
        this.id = id;
        this.object = object;
        this.url = url;
        this.dataUpdatedAt = dataUpdatedAt;
        this.data = data;
    }

    @Nullable
    public final Integer getId() {
        return id;
    }

    @Nullable
    public final String getObject() {
        return object;
    }

    @Nullable
    public final WaniKaniSubjectDataApiResponse getData() {
        return data;
    }

    @NonNull
    @Override
    public String toString() {
        return "WaniKaniSubjectApiResponse{\n" +
                "\tid=" + id +
                ",\n\t object='" + object + '\'' +
                ",\n\t url='" + url + '\'' +
                ",\n\t dataUpdatedAt='" + dataUpdatedAt + '\'' +
                ",\n\t data=" + data +
                "\n}";
    }
}