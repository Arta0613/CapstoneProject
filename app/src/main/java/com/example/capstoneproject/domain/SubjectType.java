package com.example.capstoneproject.domain;

import androidx.annotation.NonNull;

import java.util.List;

public class SubjectType {

    @NonNull private final String character;
    @NonNull
    private final String characterImage; // if character doesn't have utf entry (radicals only)
    @NonNull private final List<Reading> readings;
    @NonNull private final String meaning;

    public SubjectType(
            @NonNull final String character,
            @NonNull final String characterImage,
            @NonNull final List<Reading> readings,
            @NonNull final String meaning
    ) {
        this.character = character;
        this.characterImage = characterImage;
        this.readings = readings;
        this.meaning = meaning;
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

    @NonNull
    @Override
    public String toString() {
        return "SubjectType{\n\t\t" +
                " character='" + character + '\'' +
                ",\n\t\t characterImage='" + characterImage + '\'' +
                ",\n\t\t readings=" + readings +
                ",\n\t\t meaning='" + meaning + '\'' +
                "\n\t}";
    }
}