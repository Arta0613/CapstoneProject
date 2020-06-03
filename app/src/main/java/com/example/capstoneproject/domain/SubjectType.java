package com.example.capstoneproject.domain;

import androidx.annotation.NonNull;

import com.example.capstoneproject.R;

import java.util.List;

public class SubjectType {

    @NonNull private final Integer subjectId;
    @NonNull private final String subjectType;
    @NonNull private final String character;
    @NonNull private final String characterImage; // if character doesn't have utf entry (radicals only)
    @NonNull private final List<Reading> readings;
    @NonNull private final String meaning;

    public SubjectType(
            @NonNull final Integer subjectId,
            @NonNull final String subjectType,
            @NonNull final String character,
            @NonNull final String characterImage,
            @NonNull final List<Reading> readings,
            @NonNull final String meaning
    ) {
        this.subjectId = subjectId;
        this.subjectType = subjectType;
        this.character = character;
        this.characterImage = characterImage;
        this.readings = readings;
        this.meaning = meaning;
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
    public final String getMeaning() {
        return meaning;
    }

    public final int getSubjectColor() {
        switch (subjectType) {
            case "radical":
                return R.color.radical_color;
            case "kanji":
                return R.color.kanji_color;
            case "vocabulary":
                return R.color.vocabulary_color;
        }

        return -1;
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
                ",\n\t\t meaning='" + meaning + '\'' +
                "\n\t}";
    }
}