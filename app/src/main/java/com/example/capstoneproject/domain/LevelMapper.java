package com.example.capstoneproject.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.capstoneproject.data.response.WaniKaniSubjectApiResponse;
import com.example.capstoneproject.data.response.WaniKaniSubjectDataApiResponse;
import com.example.capstoneproject.data.response.WaniKaniSubjectDataReadingApiResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelMapper {

    private static final int FREE_LEVELS = 3;
    private static final String SUBJECT_RADICAL = "radical";
    private static final String SUBJECT_KANJI = "kanji";
    private static final String SUBJECT_VOCABULARY = "vocabulary";

    @NonNull
    public List<Level> mapLevels(@Nullable final List<WaniKaniSubjectApiResponse> subjects) {
        final List<Level> levels = new ArrayList<>(FREE_LEVELS);
        final Set<Integer> levelsAdded = new HashSet<>();

        if (subjects != null) {
            for (final WaniKaniSubjectApiResponse subject : subjects) {
                if (subject.getObject() != null && subject.getData() != null && subject.getData().getLevel() != null) {

                    final int level = subject.getData().getLevel() - 1;

                    if (!levelsAdded.contains(level)) {
                        levels.add(level, new Level(level + 1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
                        levelsAdded.add(level);
                    }

                    if (subject.getObject().equals(SUBJECT_RADICAL) && !isRadicalOmitted(subject.getData().getHiddenAt())) {
                        levels.get(level).getRadicalList().add(mapSubjectType(subject.getId(), subject.getData()));
                    } else if (subject.getObject().equals(SUBJECT_KANJI)) {
                        levels.get(level).getKanjiList().add(mapSubjectType(subject.getId(), subject.getData()));
                    } else if (subject.getObject().equals(SUBJECT_VOCABULARY)) {
                        levels.get(level).getVocabularyList().add(mapSubjectType(subject.getId(), subject.getData()));
                    }
                }
            }
        }

        return levels;
    }

    @NonNull
    private SubjectType mapSubjectType(
            @Nullable final Integer subjectId,
            @NonNull final WaniKaniSubjectDataApiResponse dataApiResponse
    ) {
        return new SubjectType(
                subjectId != null ? subjectId : -1,
                getOrEmpty(dataApiResponse.getCharacters()),
                getOrEmpty(dataApiResponse.getCharacterImage()),
                getReadings(dataApiResponse.getReadings()),
                getOrEmpty(dataApiResponse.getFirstMeaning())
        );
    }

    @NonNull
    private List<Reading> getReadings(
            @Nullable final List<WaniKaniSubjectDataReadingApiResponse> dataReadingApiResponses
    ) {
        final List<Reading> readings = new ArrayList<>();

        if (dataReadingApiResponses != null) {
            for (final WaniKaniSubjectDataReadingApiResponse dataReadingApiResponse : dataReadingApiResponses) {
                readings.add(new Reading(
                        getOrEmpty(dataReadingApiResponse.getType()),
                        getOrEmpty(dataReadingApiResponse.getReading()),
                        getOrFalse(dataReadingApiResponse.isPrimary()),
                        getOrFalse(dataReadingApiResponse.isAcceptedAnswer())
                ));
            }
        }

        return readings;
    }

    private boolean isRadicalOmitted(@Nullable final String hiddenAt) {
        return hiddenAt != null;
    }

    @NonNull
    private String getOrEmpty(@Nullable final String nullableString) {
        return nullableString != null ? nullableString : "";
    }

    @NonNull
    private Boolean getOrFalse(@Nullable final Boolean nullableBoolean) {
        return nullableBoolean != null ? nullableBoolean : false;
    }
}