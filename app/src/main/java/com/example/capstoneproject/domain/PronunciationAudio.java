package com.example.capstoneproject.domain;

import androidx.annotation.NonNull;

public class PronunciationAudio {

    public static final String GENDER_FEMALE = "female";
    public static final String GENDER_MALE = "male";

    @NonNull private final String audioUrl;
    @NonNull private final String pronunciation;
    @NonNull private final String voiceActorName;
    @NonNull private final String gender;
    @NonNull private final String voiceDescription;

    public PronunciationAudio(
            @NonNull final String audioUrl,
            @NonNull final String pronunciation,
            @NonNull final String voiceActorName,
            @NonNull final String gender,
            @NonNull final String voiceDescription
    ) {
        this.audioUrl = audioUrl;
        this.pronunciation = pronunciation;
        this.voiceActorName = voiceActorName;
        this.gender = gender;
        this.voiceDescription = voiceDescription;
    }

    @NonNull
    public final String getAudioUrl() {
        return audioUrl;
    }

    @NonNull
    public final String getPronunciation() {
        return pronunciation;
    }

    @NonNull
    public final String getVoiceActorName() {
        return voiceActorName;
    }

    @NonNull
    public final String getGender() {
        return gender;
    }

    @NonNull
    public final String getVoiceDescription() {
        return voiceDescription;
    }

    @NonNull
    @Override
    public String toString() {
        return "PronunciationAudio{" +
                "audioUrl='" + audioUrl + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", voiceActorName='" + voiceActorName + '\'' +
                ", gender='" + gender + '\'' +
                ", voiceDescription='" + voiceDescription + '\'' +
                '}';
    }
}