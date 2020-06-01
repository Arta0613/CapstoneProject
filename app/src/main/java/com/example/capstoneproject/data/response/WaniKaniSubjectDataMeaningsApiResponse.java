package com.example.capstoneproject.data.response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class WaniKaniSubjectDataMeaningsApiResponse {

    @Nullable private final String meaning;
    @Nullable private final Boolean primary;

    @SerializedName("accepted_answer")
    @Nullable private final Boolean acceptedAnswer;

    public WaniKaniSubjectDataMeaningsApiResponse(
            @Nullable final String meaning,
            @Nullable final Boolean primary,
            @Nullable final Boolean acceptedAnswer
    ) {
        this.meaning = meaning;
        this.primary = primary;
        this.acceptedAnswer = acceptedAnswer;
    }

    @Nullable
    public final String getMeaning() {
        return meaning;
    }

    @Nullable
    public final Boolean isPrimary() {
        return primary;
    }

    @Nullable
    public final Boolean getAcceptedAnswer() {
        return acceptedAnswer;
    }

    @NonNull
    @Override
    public String toString() {
        return "WaniKaniSubjectDataMeaningsApiResponse{" +
                "meaning='" + meaning + '\'' +
                ", primary=" + primary +
                ", acceptedAnswer=" + acceptedAnswer +
                '}';
    }
}