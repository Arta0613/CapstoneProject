package com.example.capstoneproject.database;

import androidx.annotation.NonNull;

import com.example.capstoneproject.database.entities.LevelEntity;
import com.example.capstoneproject.database.entities.ReadingEntity;
import com.example.capstoneproject.database.entities.SubjectTypeEntity;
import com.example.capstoneproject.domain.Level;
import com.example.capstoneproject.domain.Reading;
import com.example.capstoneproject.domain.SubjectType;

import java.util.ArrayList;
import java.util.List;

public class LevelEntityMappers {

    @NonNull
    public final List<LevelEntity> mapLevelEntities(@NonNull final List<Level> levels) {
        final List<LevelEntity> levelEntities = new ArrayList<>();

        int levelId;

        for (final Level level : levels) {
            levelId = level.getLevelId();

            levelEntities.add(new LevelEntity(
                    levelId,
                    mapSubjectTypeEntities(levelId, level.getRadicalList()),
                    mapSubjectTypeEntities(levelId, level.getKanjiList()),
                    mapSubjectTypeEntities(levelId, level.getVocabularyList())
            ));
        }

        return levelEntities;
    }

    @NonNull
    public final List<SubjectTypeEntity> mapSubjectTypeEntities(
            @NonNull final Integer levelId, @NonNull final List<SubjectType> subjectTypes
    ) {
        final List<SubjectTypeEntity> subjectTypeEntities = new ArrayList<>();

        int subjectId;

        for (final SubjectType subjectType : subjectTypes) {
            subjectId = subjectType.getSubjectId();

            subjectTypeEntities.add(new SubjectTypeEntity(
                    subjectId,
                    levelId,
                    subjectType.getSubjectType(),
                    subjectType.getCharacter(),
                    subjectType.getCharacterImage(),
                    mapReadingEntities(subjectId, subjectType.getReadings()),
                    subjectType.getMeaning()
            ));
        }

        return subjectTypeEntities;
    }

    @NonNull
    public final List<ReadingEntity> mapReadingEntities(
            @NonNull final Integer subjectId, @NonNull final List<Reading> readings
    ) {
        final List<ReadingEntity> readingEntities = new ArrayList<>();

        for (final Reading reading : readings) {
            readingEntities.add(new ReadingEntity(
                    subjectId,
                    reading.getType(),
                    reading.getReading(),
                    reading.getPrimary(),
                    reading.getAcceptedAnswer()
            ));
        }

        return readingEntities;
    }

    @NonNull
    public final List<Level> mapLevels(@NonNull final List<LevelEntity> levelEntities) {
        final List<Level> levels = new ArrayList<>();

        int levelId;

        for (final LevelEntity levelEntity : levelEntities) {
            levelId = levelEntity.getId();

            levels.add(new Level(
                    levelId,
                    mapSubjectTypes(levelEntity.getRadicalList()),
                    mapSubjectTypes(levelEntity.getKanjiList()),
                    mapSubjectTypes(levelEntity.getVocabularyList())
            ));
        }

        return levels;
    }

    @NonNull
    public final List<SubjectType> mapSubjectTypes(
            @NonNull final List<SubjectTypeEntity> subjectTypeEntities
    ) {
        final List<SubjectType> subjectTypes = new ArrayList<>();


        for (final SubjectTypeEntity subjectTypeEntity : subjectTypeEntities) {

            subjectTypes.add(new SubjectType(
                    subjectTypeEntity.getSubjectId(),
                    subjectTypeEntity.getSubjectType(),
                    subjectTypeEntity.getCharacters(),
                    subjectTypeEntity.getCharacterImage(),
                    mapReadings(subjectTypeEntity.getReadingsList()),
                    subjectTypeEntity.getMeaning()
            ));
        }

        return subjectTypes;
    }

    @NonNull
    public final List<Reading> mapReadings(@NonNull final List<ReadingEntity> readingEntities) {
        final List<Reading> readingList = new ArrayList<>();

        for (final ReadingEntity readingEntity : readingEntities) {
            readingList.add(new Reading(
                    readingEntity.getType(),
                    readingEntity.getReading(),
                    readingEntity.getPrimary(),
                    readingEntity.getAcceptedAnswer()
            ));
        }

        return readingList;
    }
}