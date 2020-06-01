package com.example.capstoneproject.data.response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// SubjectType fields are shared by Radicals, Kanji and Vocab unless otherwise stated
@SuppressWarnings("unused")
public class WaniKaniSubjectDataApiResponse {

    @SerializedName("created_at")
    @Nullable private final String createdAt;

    @Nullable private final Integer level;
    @Nullable private final String slug;

    @SerializedName("hidden_at")
    @Nullable private final String hiddenAt;

    @SerializedName("document_url")
    @Nullable private final String documentUrl;

    @Nullable
    private final String characters; // Shared however some Radicals can be null (no UTF entry)

    @SerializedName("character_images") // Radical only
    @Nullable private final List<WaniKaniSubjectDataCharacterImageApiResponse> characterImages;

    @Nullable private final List<WaniKaniSubjectDataMeaningsApiResponse> meanings;

    @SerializedName("auxiliary_meanings")
    @Nullable private final List<WaniKaniSubjectDataAuxiliaryMeaningApiResponse> auxiliaryMeanings;

    @Nullable private final List<WaniKaniSubjectDataReadingApiResponse> readings;

    @SerializedName("parts_of_speech") // Vocab only
    @Nullable private final List<String> partsOfSpeech;

    @SerializedName("amalgamation_subject_ids") // Radical and Kanji
    @Nullable private final List<Integer> amalgamationSubjectIds;

    @SerializedName("component_subject_ids") // Kanji and Vocabulary
    @Nullable private final List<Integer> componentSubjectIds;

    @SerializedName("visually_similar_subject_ids") // Kanji only
    @Nullable private final List<Integer> visuallySimilarSubjectIds;

    @SerializedName("meaning_mnemonic")
    @Nullable private final String meaningMnemonic;

    @SerializedName("meaning_hint") // Kanji only
    @Nullable private final String meaningHint;

    @SerializedName("reading_mnemonic") // Kanji only
    @Nullable private final String readingMnemonic;

    @SerializedName("reading_hint") // Kanji only
    @Nullable private final String readingHint;

    @SerializedName("context_sentences") // Vocabulary only
    @Nullable private final List<WaniKaniSubjectDataContextSentenceApiResponse> contextSentences;

    @SerializedName("pronunciation_audios") // Vocabulary only
    @Nullable
    private final List<WaniKaniSubjectDataPronunciationAudioApiResponse> pronunciationAudios;

    @SerializedName("lesson_position")
    @Nullable private final Integer lessonPosition;

    @SerializedName("spaced_repetition_system_id")
    @Nullable private final Integer spacedRepetitionSystemId;

    public WaniKaniSubjectDataApiResponse(
            @Nullable final String createdAt,
            @Nullable final Integer level,
            @Nullable final String slug,
            @Nullable final String hiddenAt,
            @Nullable final String documentUrl,
            @Nullable final String characters,
            @Nullable final List<WaniKaniSubjectDataCharacterImageApiResponse> characterImages,
            @Nullable final List<WaniKaniSubjectDataMeaningsApiResponse> meanings,
            @Nullable final List<WaniKaniSubjectDataAuxiliaryMeaningApiResponse> auxiliaryMeanings,
            @Nullable final List<WaniKaniSubjectDataReadingApiResponse> readings,
            @Nullable final List<String> partsOfSpeech,
            @Nullable final List<Integer> amalgamationSubjectIds,
            @Nullable final List<Integer> componentSubjectIds,
            @Nullable final List<Integer> visuallySimilarSubjectIds,
            @Nullable final String meaningMnemonic,
            @Nullable final String meaningHint,
            @Nullable final String readingMnemonic,
            @Nullable final String readingHint,
            @Nullable final List<WaniKaniSubjectDataContextSentenceApiResponse> contextSentences,
            @Nullable final List<WaniKaniSubjectDataPronunciationAudioApiResponse> pronunciationAudios,
            @Nullable final Integer lessonPosition,
            @Nullable final Integer spacedRepetitionSystemId
    ) {
        this.createdAt = createdAt;
        this.level = level;
        this.slug = slug;
        this.hiddenAt = hiddenAt;
        this.documentUrl = documentUrl;
        this.characters = characters;
        this.characterImages = characterImages;
        this.meanings = meanings;
        this.auxiliaryMeanings = auxiliaryMeanings;
        this.readings = readings;
        this.partsOfSpeech = partsOfSpeech;
        this.amalgamationSubjectIds = amalgamationSubjectIds;
        this.componentSubjectIds = componentSubjectIds;
        this.visuallySimilarSubjectIds = visuallySimilarSubjectIds;
        this.meaningMnemonic = meaningMnemonic;
        this.meaningHint = meaningHint;
        this.readingMnemonic = readingMnemonic;
        this.readingHint = readingHint;
        this.contextSentences = contextSentences;
        this.pronunciationAudios = pronunciationAudios;
        this.lessonPosition = lessonPosition;
        this.spacedRepetitionSystemId = spacedRepetitionSystemId;
    }

    @Nullable
    public final Integer getLevel() {
        return level;
    }

    @Nullable
    public final String getHiddenAt() {
        return hiddenAt;
    }

    @Nullable
    public final String getCharacters() {
        return characters;
    }

    @Nullable
    public final String getCharacterImage() { // get only character image with styles applied
        if (characterImages != null) {
            for (final WaniKaniSubjectDataCharacterImageApiResponse characterImage : characterImages) {
                if (characterImage.getContentType() != null && characterImage.getContentType().contains("svg") &&
                        characterImage.getMetadata() != null && characterImage.getMetadata().getInlineStyles() != null && characterImage.getMetadata().getInlineStyles()) {
                    return characterImage.getUrl();
                }
            }
        }

        return null;
    }

    @Nullable
    public final List<WaniKaniSubjectDataMeaningsApiResponse> getMeanings() {
        return meanings;
    }

    @Nullable
    public final String getFirstMeaning() {
        if (meanings != null) {
            for (final WaniKaniSubjectDataMeaningsApiResponse meaning : meanings) {
                if (meaning.isPrimary() != null && meaning.isPrimary()) {
                    return meaning.getMeaning();
                }
            }
        }

        return null;
    }

    @Nullable
    public final List<WaniKaniSubjectDataReadingApiResponse> getReadings() {
        return readings;
    }

    @NonNull
    @Override
    public String toString() {
        return "WaniKaniSubjectDataApiResponse{\n" +
                "\t\tcreatedAt='" + createdAt + '\'' +
                ",\n\t\t level=" + level +
                ",\n\t\t slug='" + slug + '\'' +
                ",\n\t\t hiddenAt='" + hiddenAt + '\'' +
                ",\n\t\t documentUrl='" + documentUrl + '\'' +
                ",\n\t\t characters='" + characters + '\'' +
                ",\n\t\t characterImages=" + characterImages +
                ",\n\t\t meanings=" + meanings +
                ",\n\t\t auxiliaryMeanings=" + auxiliaryMeanings +
                ",\n\t\t readings=" + readings +
                ",\n\t\t partsOfSpeech=" + partsOfSpeech +
                ",\n\t\t amalgamationSubjectIds=" + amalgamationSubjectIds +
                ",\n\t\t componentSubjectIds=" + componentSubjectIds +
                ",\n\t\t visuallySimilarSubjectIds=" + visuallySimilarSubjectIds +
                ",\n\t\t meaningMnemonic='" + meaningMnemonic + '\'' +
                ",\n\t\t meaningHint='" + meaningHint + '\'' +
                ",\n\t\t readingMnemonic='" + readingMnemonic + '\'' +
                ",\n\t\t readingHint='" + readingHint + '\'' +
                ",\n\t\t contextSentences='" + contextSentences + '\'' +
                ",\n\t\t pronunciationAudios='" + pronunciationAudios + '\'' +
                ",\n\t\t lessonPosition=" + lessonPosition +
                ",\n\t\t spacedRepetitionSystemId=" + spacedRepetitionSystemId +
                "\n\t}";
    }

    @SuppressWarnings("unused")
    class WaniKaniSubjectDataAuxiliaryMeaningApiResponse {

        @Nullable private final String meaning;
        @Nullable private final String type;

        public WaniKaniSubjectDataAuxiliaryMeaningApiResponse(
                @Nullable final String meaning,
                @Nullable final String type
        ) {
            this.meaning = meaning;
            this.type = type;
        }

        @NonNull
        @Override
        public String toString() {
            return "WaniKaniSubjectDataAuxiliaryMeaningApiResponse{" +
                    "meaning='" + meaning + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}