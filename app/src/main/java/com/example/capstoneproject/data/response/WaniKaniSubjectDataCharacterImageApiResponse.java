package com.example.capstoneproject.data.response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class WaniKaniSubjectDataCharacterImageApiResponse {

    @Nullable private final String url;
    @Nullable private final WaniKaniSubjectDataCharacterImageMetadataApiResponse metadata;

    @SerializedName("content_type")
    @Nullable private final String contentType;

    public WaniKaniSubjectDataCharacterImageApiResponse(
            @Nullable final String url,
            @Nullable final WaniKaniSubjectDataCharacterImageMetadataApiResponse metadata,
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
    public final WaniKaniSubjectDataCharacterImageMetadataApiResponse getMetadata() {
        return metadata;
    }

    @Nullable
    public final String getContentType() {
        return contentType;
    }

    @NonNull
    @Override
    public String toString() {
        return "WaniKaniSubjectDataCharacterImageApiResponse{" +
                "url='" + url + '\'' +
                ", metadata=" + metadata +
                ", contentType='" + contentType + '\'' +
                '}';
    }

    @SuppressWarnings("unused")
    class WaniKaniSubjectDataCharacterImageMetadataApiResponse {

        @SerializedName("inline_styles")
        @Nullable private final Boolean inlineStyles;

        @Nullable private final String color;
        @Nullable private final String dimensions;

        @SerializedName("style_name")
        @Nullable private final String styleName;

        public WaniKaniSubjectDataCharacterImageMetadataApiResponse(
                @Nullable final Boolean inlineStyles,
                @Nullable final String color,
                @Nullable final String dimensions,
                @Nullable final String styleName
        ) {
            this.inlineStyles = inlineStyles;
            this.color = color;
            this.dimensions = dimensions;
            this.styleName = styleName;
        }

        @Nullable
        public Boolean getInlineStyles() {
            return inlineStyles;
        }

        @NonNull
        @Override
        public String toString() {
            return "WaniKaniSubjectDataCharacterImageMetadataApiResponse{" +
                    "inlineStyles=" + inlineStyles +
                    ", color='" + color + '\'' +
                    ", dimensions='" + dimensions + '\'' +
                    ", styleName='" + styleName + '\'' +
                    '}';
        }
    }
}