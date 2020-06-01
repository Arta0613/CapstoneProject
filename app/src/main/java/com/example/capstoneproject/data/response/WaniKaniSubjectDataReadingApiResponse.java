package com.example.capstoneproject.data.response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class WaniKaniSubjectDataReadingApiResponse {

    @Nullable private final String reading;
    @Nullable private final Boolean primary;

    @SerializedName("accepted_answer")
    @Nullable private final Boolean acceptedAnswer;

    @Nullable private final String type; // Kanji

    public WaniKaniSubjectDataReadingApiResponse(
            @Nullable final String reading,
            @Nullable final Boolean primary,
            @Nullable final Boolean acceptedAnswer,
            @Nullable final String type
    ) {
        this.reading = reading;
        this.primary = primary;
        this.acceptedAnswer = acceptedAnswer;
        this.type = type;
    }

    @Nullable
    public final String getReading() {
        return reading;
    }

    @Nullable
    public final Boolean isPrimary() {
        return primary;
    }

    @Nullable
    public final Boolean isAcceptedAnswer() {
        return acceptedAnswer;
    }

    @Nullable
    public final String getType() {
        return type;
    }

    @NonNull
    @Override
    public String toString() {
        return "WaniKaniSubjectDataReadingApiResponse{" +
                "reading='" + reading + '\'' +
                ", primary=" + primary +
                ", acceptedAnswer=" + acceptedAnswer +
                ", type='" + type + '\'' +
                '}';
    }
}