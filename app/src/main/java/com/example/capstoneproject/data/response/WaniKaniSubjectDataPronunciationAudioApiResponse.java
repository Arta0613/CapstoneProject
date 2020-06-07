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

    @Nullable
    public final String getUrl() {
        return url;
    }

    @Nullable
    public final WaniKaniSubjectDataPronunciationAudioMetadataApiResponse getMetadata() {
        return metadata;
    }

    @Nullable
    public final String getContentType() {
        return contentType;
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
    public class WaniKaniSubjectDataPronunciationAudioMetadataApiResponse {

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

        @Nullable
        public final String getGender() {
            return gender;
        }

        @Nullable
        public final Integer getSourceId() {
            return sourceId;
        }

        @Nullable
        public final String getPronunciation() {
            return pronunciation;
        }

        @Nullable
        public final Integer getVoiceActorId() {
            return voiceActorId;
        }

        @Nullable
        public final String getVoiceActorName() {
            return voiceActorName;
        }

        @Nullable
        public final String getVoiceDescription() {
            return voiceDescription;
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