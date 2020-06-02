package com.example.capstoneproject.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.capstoneproject.database.SubjectTypeEntityTypeConverter;

import java.util.List;

@Entity
public class LevelEntity {

    @NonNull
    @PrimaryKey
    private final Integer id;

    @NonNull
    @TypeConverters(SubjectTypeEntityTypeConverter.class)
    private final List<SubjectTypeEntity> radicalList;

    @NonNull
    @TypeConverters(SubjectTypeEntityTypeConverter.class)
    private final List<SubjectTypeEntity> kanjiList;

    @NonNull
    @TypeConverters(SubjectTypeEntityTypeConverter.class)
    private final List<SubjectTypeEntity> vocabularyList;

    public LevelEntity(
            @NonNull final Integer id,
            @NonNull final List<SubjectTypeEntity> radicalList,
            @NonNull final List<SubjectTypeEntity> kanjiList,
            @NonNull final List<SubjectTypeEntity> vocabularyList
    ) {
        this.id = id;
        this.radicalList = radicalList;
        this.kanjiList = kanjiList;
        this.vocabularyList = vocabularyList;
    }

    @NonNull
    public final Integer getId() {
        return id;
    }

    @NonNull
    public final List<SubjectTypeEntity> getRadicalList() {
        return radicalList;
    }

    @NonNull
    public final List<SubjectTypeEntity> getKanjiList() {
        return kanjiList;
    }

    @NonNull
    public final List<SubjectTypeEntity> getVocabularyList() {
        return vocabularyList;
    }

    @NonNull
    @Override
    public String toString() {
        return "LevelEntity{" +
                "id=" + id +
                ", radicalList=" + radicalList +
                ", kanjiList=" + kanjiList +
                ", vocabularyList=" + vocabularyList +
                '}';
    }
}