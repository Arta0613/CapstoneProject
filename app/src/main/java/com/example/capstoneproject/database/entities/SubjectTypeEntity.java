package com.example.capstoneproject.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

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

    @NonNull private final String characters;
    @NonNull private final String characterImage;

    @NonNull
    @TypeConverters(ReadingEntityTypeConverter.class)
    private final List<ReadingEntity> readingsList;

    @NonNull private final String meaning;

    public SubjectTypeEntity(
            @NonNull final Integer subjectId,
            @NonNull final Integer levelId,
            @NonNull final String characters,
            @NonNull final String characterImage,
            @NonNull final List<ReadingEntity> readingsList,
            @NonNull final String meaning
    ) {
        this.subjectId = subjectId;
        this.levelId = levelId;
        this.characters = characters;
        this.characterImage = characterImage;
        this.readingsList = readingsList;
        this.meaning = meaning;
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
    public final String getMeaning() {
        return meaning;
    }

    @NonNull
    @Override
    public String toString() {
        return "SubjectTypeEntity{" +
                "subjectId=" + subjectId +
                ", levelId=" + levelId +
                ", characters='" + characters + '\'' +
                ", characterImage='" + characterImage + '\'' +
                ", readingsList=" + readingsList +
                ", meaning='" + meaning + '\'' +
                '}';
    }
}