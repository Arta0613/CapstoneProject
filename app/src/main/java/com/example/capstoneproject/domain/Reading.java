package com.example.capstoneproject.domain;

import androidx.annotation.NonNull;

public class Reading {

    @NonNull private final String type;
    @NonNull private final String reading;
    @NonNull private final Boolean primary;
    @NonNull private final Boolean acceptedAnswer;

    public Reading(
            @NonNull final String type,
            @NonNull final String reading,
            @NonNull final Boolean primary,
            @NonNull final Boolean acceptedAnswer
    ) {
        this.type = type;
        this.reading = reading;
        this.primary = primary;
        this.acceptedAnswer = acceptedAnswer;
    }

    @NonNull
    public final String getType() {
        return type;
    }

    @NonNull
    public final String getReading() {
        return reading;
    }

    @NonNull
    public final Boolean getPrimary() {
        return primary;
    }

    @NonNull
    public final Boolean getAcceptedAnswer() {
        return acceptedAnswer;
    }

    @NonNull
    @Override
    public String toString() {
        return "Reading{" +
                "type='" + type + '\'' +
                ", reading='" + reading + '\'' +
                ", primary=" + primary +
                ", acceptedAnswer=" + acceptedAnswer +
                '}';
    }
}
