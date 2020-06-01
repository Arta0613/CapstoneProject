package com.example.capstoneproject.domain;

import androidx.annotation.NonNull;

import java.util.List;

public class Level {

    @NonNull private final Integer levelId;
    @NonNull private final List<SubjectType> radicals;
    @NonNull private final List<SubjectType> kanji;
    @NonNull private final List<SubjectType> vocabulary;

    public Level(
            @NonNull final Integer levelId,
            @NonNull final List<SubjectType> radicals,
            @NonNull final List<SubjectType> kanji,
            @NonNull final List<SubjectType> vocabulary
    ) {
        this.levelId = levelId;
        this.radicals = radicals;
        this.kanji = kanji;
        this.vocabulary = vocabulary;
    }

    @NonNull
    public final Integer getLevelId() {
        return levelId;
    }

    @NonNull
    public final List<SubjectType> getRadicalList() {
        return radicals;
    }

    @NonNull
    public final List<SubjectType> getKanjiList() {
        return kanji;
    }

    @NonNull
    public final List<SubjectType> getVocabularyList() {
        return vocabulary;
    }

    @NonNull
    @Override
    public String toString() {
        return "Level{\n" +
                "levelId=" + levelId +
                ",\n radicals=" + radicals +
                ",\n kanji=" + kanji +
                ",\n vocabulary=" + vocabulary +
                "\n}";
    }
}
