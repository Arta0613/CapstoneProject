package com.example.capstoneproject.data.response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class WaniKaniSubjectDataPronunciationAudioApiResponse {

    @Nullable private final String url;
    @Nullable private final WaniKaniSubjectDataPronunciationAudioMetadataApiResponse metadata;

    @SerializedName("content_type")
    @Nullable private final String contentType;

    public WaniKaniSubjectDataPronunciationAudioApiResponse(
            @Nullable final String url,
            @Nullable final WaniKaniSubjectDataPronunciationAudioMetadataApiResponse metadata,
            @Nullable final String contentType
    ) {
        this.url = url;
        this.metadata = metadata;
        this.contentType = contentType;
    }

    @NonNull
    @Override
    public String toString() {
        return "WaniKaniSubjectDataPronunciationAudioApiResponse{" +
                "url='" + url + '\'' +
                ", metadata=" + metadata +
                ", contentType='" + contentType + '\'' +
                '}';
    }

    @SuppressWarnings("unused")
    class WaniKaniSubjectDataPronunciationAudioMetadataApiResponse {

        @Nullable private final String gender;

        @SerializedName("source_id")
        @Nullable private final Integer sourceId;

        @Nullable private final String pronunciation;

        @SerializedName("voice_actor_id")
        @Nullable private final Integer voiceActorId;

        @SerializedName("voice_actor_name")
        @Nullable private final String voiceActorName;

        @SerializedName("voice_description")
        @Nullable private final String voiceDescription;

        public WaniKaniSubjectDataPronunciationAudioMetadataApiResponse(
                @Nullable final String gender,
                @Nullable final Integer sourceId,
                @Nullable final String pronunciation,
                @Nullable final Integer voiceActorId,
                @Nullable final String voiceActorName,
                @Nullable final String voiceDescription
        ) {
            this.gender = gender;
            this.sourceId = sourceId;
            this.pronunciation = pronunciation;
            this.voiceActorId = voiceActorId;
            this.voiceActorName = voiceActorName;
            this.voiceDescription = voiceDescription;
        }

        @NonNull
        @Override
        public String toString() {
            return "WaniKaniSubjectDataPronunciationAudioMetadataApiResponse{" +
                    "gender='" + gender + '\'' +
                    ", sourceId=" + sourceId +
                    ", pronunciation='" + pronunciation + '\'' +
                    ", voiceActorId=" + voiceActorId +
                    ", voiceActorName='" + voiceActorName + '\'' +
                    ", voiceDescription='" + voiceDescription + '\'' +
                    '}';
        }
    }
}