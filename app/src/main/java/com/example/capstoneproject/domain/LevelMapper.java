package com.example.capstoneproject.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.capstoneproject.data.response.WaniKaniSubjectApiResponse;
import com.example.capstoneproject.data.response.WaniKaniSubjectDataPronunciationAudioApiResponse;
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
                        levels.get(level).getRadicalList().add(mapSubjectType(subject));
                    } else if (subject.getObject().equals(SUBJECT_KANJI)) {
                        levels.get(level).getKanjiList().add(mapSubjectType(subject));
                    } else if (subject.getObject().equals(SUBJECT_VOCABULARY)) {
                        levels.get(level).getVocabularyList().add(mapSubjectType(subject));
                    }
                }
            }
        }

        return levels;
    }

    @NonNull
    private SubjectType mapSubjectType(@NonNull final WaniKaniSubjectApiResponse subject) {
        if (subject.getData() != null) {
            final String typeOfSubject = getOrEmpty(subject.getObject());
            final List<Reading> readingList = getReadings(subject.getData().getReadings(), typeOfSubject);
            String vocabularyPrimaryReading = "";
            if (typeOfSubject.equals(SUBJECT_VOCABULARY) && readingList.size() == 1) {
                vocabularyPrimaryReading = readingList.get(0).getReading();
            }

            final SubjectType subjectType = new SubjectType(
                    subject.getId() != null ? subject.getId() : -1,
                    getOrEmpty(subject.getObject()),
                    getOrEmpty(subject.getData().getCharacters()),
                    getOrEmpty(subject.getData().getCharacterImage()),
                    readingList,
                    getPronunciations(subject.getData().getPronunciationAudios(), vocabularyPrimaryReading),
                    getOrEmpty(subject.getData().getFirstMeaning()),
                    getOrEmpty(subject.getData().getMeaningMnemonic())
            );

            subjectType.getPronunciations().sort((o1, o2) -> Integer.compare((int) o1.getGender().charAt(0), (int) o2.getGender().charAt(0)));

            return subjectType;
        }

        return new SubjectType(-1, "", "", "", new ArrayList<>(), new ArrayList<>(), "", "");
    }

    @NonNull
    private List<Reading> getReadings(
            @Nullable final List<WaniKaniSubjectDataReadingApiResponse> dataReadingApiResponses,
            @NonNull final String typeOfSubject
    ) {
        final List<Reading> readings = new ArrayList<>();

        if (dataReadingApiResponses != null) {
            for (final WaniKaniSubjectDataReadingApiResponse dataReadingApiResponse : dataReadingApiResponses) {
                if (typeOfSubject.equals(SUBJECT_VOCABULARY) && !getOrFalse(dataReadingApiResponse.isPrimary())) {
                    continue;
                }

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

    @NonNull
    private List<PronunciationAudio> getPronunciations(
            @Nullable final List<WaniKaniSubjectDataPronunciationAudioApiResponse> pronunciationAudioApiResponses,
            @NonNull final String vocabularyPrimaryReading
    ) {
        final List<PronunciationAudio> pronunciationAudios = new ArrayList<>();

        if (pronunciationAudioApiResponses != null) {
            for (final WaniKaniSubjectDataPronunciationAudioApiResponse pronunciationAudioApiResponse : pronunciationAudioApiResponses) {

                if (pronunciationAudioApiResponse.getMetadata() == null ||
                        pronunciationAudioApiResponse.getContentType() != null && pronunciationAudioApiResponse.getContentType().contains("ogg") ||
                        pronunciationAudioApiResponse.getMetadata().getPronunciation() != null && !pronunciationAudioApiResponse.getMetadata().getPronunciation().equals(vocabularyPrimaryReading)
                ) {
                    continue;
                }

                pronunciationAudios.add(new PronunciationAudio(
                        getOrEmpty(pronunciationAudioApiResponse.getUrl()),
                        getOrEmpty(pronunciationAudioApiResponse.getMetadata().getPronunciation()),
                        getOrEmpty(pronunciationAudioApiResponse.getMetadata().getVoiceActorName()),
                        getOrEmpty(pronunciationAudioApiResponse.getMetadata().getGender()),
                        getOrEmpty(pronunciationAudioApiResponse.getMetadata().getVoiceDescription())
                ));
            }
        }

        return pronunciationAudios;
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