package com.example.capstoneproject.domain;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import com.example.capstoneproject.utils.SubjectHelper;

import java.util.List;

import static com.example.capstoneproject.domain.PronunciationAudio.GENDER_FEMALE;
import static com.example.capstoneproject.domain.PronunciationAudio.GENDER_MALE;

public class SubjectType {

    public static final String RADICAL = "radical";
    public static final String KANJI = "kanji";
    public static final String VOCABULARY = "vocabulary";

    private static final String ONYOMI = "onyomi";
    private static final String KUNYOMI = "kunyomi";
    private static final String NANORI = "nanori";

    private static final String INFO_NAME = "name";
    private static final String INFO_DESCRIPTION = "description";
    private static final String INFO_READING = "reading";

    @NonNull private final Integer subjectId;
    @NonNull private final String subjectType;
    @NonNull private final String character;
    @NonNull
    private final String characterImage; // if character doesn't have utf entry (radicals only)
    @NonNull private final List<Reading> readings;
    @NonNull private final List<PronunciationAudio> pronunciations;
    @NonNull private final String meaning;
    @NonNull private final String meaningMnemonic;

    public SubjectType(
            @NonNull final Integer subjectId,
            @NonNull final String subjectType,
            @NonNull final String character,
            @NonNull final String characterImage,
            @NonNull final List<Reading> readings,
            @NonNull final List<PronunciationAudio> pronunciations,
            @NonNull final String meaning,
            @NonNull final String meaningMnemonic
    ) {
        this.subjectId = subjectId;
        this.subjectType = subjectType;
        this.character = character;
        this.characterImage = characterImage;
        this.readings = readings;
        this.pronunciations = pronunciations;
        this.meaning = meaning;
        this.meaningMnemonic = meaningMnemonic;
    }

    @NonNull
    public Integer getSubjectId() {
        return subjectId;
    }

    @NonNull
    public final String getSubjectType() {
        return subjectType;
    }

    @NonNull
    public final String getCharacter() {
        return character;
    }

    @NonNull
    public final String getCharacterImage() {
        return characterImage;
    }

    @NonNull
    public final List<Reading> getReadings() {
        return readings;
    }

    @NonNull
    public final List<PronunciationAudio> getPronunciations() {
        return pronunciations;
    }

    @NonNull
    public final String getMeaning() {
        return meaning;
    }

    @NonNull
    public final String getMeaningMnemonic() {
        return meaningMnemonic;
    }

    // Helpers - TODO: Wrap SubjectType in wrapper class
    @ColorRes
    public final int getSubjectColor() {
        return SubjectHelper.getColor(subjectType);
    }

    public final boolean isRadical() {
        return subjectType.equals(RADICAL);
    }

    public final boolean isKanji() {
        return subjectType.equals(KANJI);
    }

    public final boolean isVocabulary() {
        return subjectType.equals(VOCABULARY);
    }

    @NonNull
    public final String getKanjiOnyomiReading() {
        return getKanjiReadingsOf(ONYOMI);
    }

    @NonNull
    public final String getKanjiKunyomiReading() {
        return getKanjiReadingsOf(KUNYOMI);
    }

    @NonNull
    public final String getKanjiNanoriReading() {
        return getKanjiReadingsOf(NANORI);
    }

    @NonNull
    public final String getVocabularyReading() {
        if (readings.size() == 1) {
            return readings.get(0).getReading();
        }

        for (final Reading reading : readings) {
            if (reading.getPrimary()) {
                return reading.getReading();
            }
        }

        return "";
    }

    @NonNull
    public final String getFemaleVoiceActorName() {
        return getVoiceActorName(GENDER_FEMALE);
    }

    @NonNull
    public final String getMaleVoiceActorName() {
        return getVoiceActorName(GENDER_MALE);
    }

    @NonNull
    private String getVoiceActorName(@NonNull final String gender) {
        if (pronunciations.size() == 2) {
            switch (gender) {
                case GENDER_FEMALE:
                    return pronunciations.get(0).getVoiceActorName();
                case GENDER_MALE:
                    return pronunciations.get(1).getVoiceActorName();
            }
        }

        return getVoiceInfo(INFO_NAME, gender);
    }

    @NonNull
    public final String getFemaleVoiceDescription() {
        return getVoiceDescription(GENDER_FEMALE);
    }

    @NonNull
    public final String getMaleVoiceDescription() {
        return getVoiceDescription(GENDER_MALE);
    }

    @NonNull
    private String getVoiceDescription(@NonNull final String gender) {
        if (pronunciations.size() == 2) {
            switch (gender) {
                case GENDER_FEMALE:
                    return (pronunciations.get(0).getVoiceDescription() + ", " + pronunciations.get(0).getGender()).toUpperCase();
                case GENDER_MALE:
                    return (pronunciations.get(1).getVoiceDescription() + ", " + pronunciations.get(1).getGender()).toUpperCase();
            }
        }

        return getVoiceInfo(INFO_DESCRIPTION, gender);
    }

    @NonNull
    public final String getFemaleVoiceReading() {
        return getVoiceReading(GENDER_FEMALE);
    }

    @NonNull
    public final String getMaleVoiceReading() {
        return getVoiceReading(GENDER_MALE);
    }

    @NonNull
    private String getVoiceReading(@NonNull final String gender) {
        if (pronunciations.size() == 2) {
            switch (gender) {
                case GENDER_FEMALE:
                    return pronunciations.get(0).getAudioUrl();
                case GENDER_MALE:
                    return pronunciations.get(1).getAudioUrl();
            }
        }

        return getVoiceInfo(INFO_READING, gender);
    }

    @NonNull
    private String getVoiceInfo(
            @NonNull final String infoType, @NonNull final String gender
    ) { // TODO: make use of enum
        for (final PronunciationAudio pronunciation : pronunciations) {
            if (pronunciation.getPronunciation().equals(getVocabularyReading()) &&
                    pronunciation.getGender().equals(gender)
            ) {
                switch (infoType) {
                    case INFO_NAME:
                        switch (gender) {
                            case GENDER_FEMALE:
                                return (pronunciations.get(0).getVoiceDescription() + ", " + pronunciations.get(0).getGender()).toUpperCase();
                            case GENDER_MALE:
                                return (pronunciations.get(1).getVoiceDescription() + ", " + pronunciations.get(1).getGender()).toUpperCase();
                        }
                    case INFO_DESCRIPTION:
                        return pronunciation.getVoiceDescription() + ", " + pronunciation.getGender();
                    case INFO_READING:
                        return pronunciation.getAudioUrl();

                }
            }
        }

        return "Not Found";
    }

    @NonNull
    private String getKanjiReadingsOf(@NonNull final String type) {
        StringBuilder readingType = new StringBuilder();

        for (final Reading reading : readings) {
            if (reading.getType().equals(type)) {
                readingType.append(reading.getReading());
                readingType.append(", ");
            }
        }

        if (!readingType.toString().isEmpty()) {
            readingType.replace(readingType.length() - 2, readingType.length(), "");
        }

        return readingType.toString().isEmpty() ? "None" : readingType.toString();
    }

    @NonNull
    @Override
    public final String toString() {
        return "SubjectType{\n\t\t" +
                " subjectId='" + subjectId + '\'' +
                ",\n\t\t subjectType='" + subjectType + '\'' +
                ",\n\t\t character='" + character + '\'' +
                ",\n\t\t characterImage='" + characterImage + '\'' +
                ",\n\t\t readings=" + readings +
                ",\n\t\t pronunciations=" + pronunciations +
                ",\n\t\t meaning='" + meaning + '\'' +
                ",\n\t\t meaningMnemonic='" + meaningMnemonic + '\'' +
                "\n\t}";
    }
}