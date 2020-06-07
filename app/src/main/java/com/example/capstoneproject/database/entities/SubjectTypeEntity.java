package com.example.capstoneproject.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.capstoneproject.database.PronunciationAudioEntityTypeConverter;
import com.example.capstoneproject.database.ReadingEntityTypeConverter;

import java.util.List;

@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = LevelEntity.class,
                        parentColumns = "id",
                        childColumns = "levelId",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class SubjectTypeEntity {

    @NonNull
    @PrimaryKey
    private final Integer subjectId;

    @NonNull
    @ColumnInfo(index = true)
    private final Integer levelId;

    @NonNull private final String subjectType;
    @NonNull private final String characters;
    @NonNull private final String characterImage;

    @NonNull
    @TypeConverters(ReadingEntityTypeConverter.class)
    private final List<ReadingEntity> readingsList;

    @NonNull
    @TypeConverters(PronunciationAudioEntityTypeConverter.class)
    private final List<PronunciationAudioEntity> pronunciationsList;

    @NonNull private final String meaning;
    @NonNull private final String meaningMnemonic;

    public SubjectTypeEntity(
            @NonNull final Integer subjectId,
            @NonNull final Integer levelId,
            @NonNull final String subjectType,
            @NonNull final String characters,
            @NonNull final String characterImage,
            @NonNull final List<ReadingEntity> readingsList,
            @NonNull final List<PronunciationAudioEntity> pronunciationsList,
            @NonNull final String meaning,
            @NonNull final String meaningMnemonic
    ) {
        this.subjectId = subjectId;
        this.levelId = levelId;
        this.subjectType = subjectType;
        this.characters = characters;
        this.characterImage = characterImage;
        this.readingsList = readingsList;
        this.pronunciationsList = pronunciationsList;
        this.meaning = meaning;
        this.meaningMnemonic = meaningMnemonic;
    }

    @NonNull
    public final Integer getSubjectId() {
        return subjectId;
    }

    @NonNull
    public final Integer getLevelId() {
        return levelId;
    }

    @NonNull
    public final String getSubjectType() {
        return subjectType;
    }

    @NonNull
    public final String getCharacters() {
        return characters;
    }

    @NonNull
    public final String getCharacterImage() {
        return characterImage;
    }

    @NonNull
    public final List<ReadingEntity> getReadingsList() {
        return readingsList;
    }

    @NonNull
    public final List<PronunciationAudioEntity> getPronunciationsList() {
        return pronunciationsList;
    }

    @NonNull
    public final String getMeaning() {
        return meaning;
    }

    @NonNull
    public String getMeaningMnemonic() {
        return meaningMnemonic;
    }

    @NonNull
    @Override
    public String toString() {
        return "SubjectTypeEntity{" +
                "subjectId=" + subjectId +
                ", levelId=" + levelId +
                ", subjectType=" + subjectType +
                ", characters='" + characters + '\'' +
                ", characterImage='" + characterImage + '\'' +
                ", readingsList=" + readingsList +
                ", pronunciationsList=" + pronunciationsList +
                ", meaning='" + meaning + '\'' +
                ", meaningMnemonic='" + meaningMnemonic + '\'' +
                '}';
    }
}