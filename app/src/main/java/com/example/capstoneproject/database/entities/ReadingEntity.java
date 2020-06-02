package com.example.capstoneproject.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = SubjectTypeEntity.class,
                        parentColumns = "subjectId",
                        childColumns = "readingsOfSubjectId",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class ReadingEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer readingId;

    @ColumnInfo(index = true)
    private Integer readingsOfSubjectId;

    private String type;
    private String reading;
    private Boolean primary;
    private Boolean acceptedAnswer;


    public ReadingEntity() {
    }

    @Ignore
    public ReadingEntity(
            @NonNull final Integer subjectId,
            @NonNull final String type,
            @NonNull final String reading,
            @NonNull final Boolean primary,
            @NonNull final Boolean acceptedAnswer
    ) {
        this.readingsOfSubjectId = subjectId;
        this.type = type;
        this.reading = reading;
        this.primary = primary;
        this.acceptedAnswer = acceptedAnswer;
    }

    public Integer getReadingId() {
        return readingId;
    }

    public void setReadingId(final Integer readingId) {
        this.readingId = readingId;
    }

    public Integer getReadingsOfSubjectId() {
        return readingsOfSubjectId;
    }

    public void setReadingsOfSubjectId(final Integer readingsOfSubjectId) {
        this.readingsOfSubjectId = readingsOfSubjectId;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(final String reading) {
        this.reading = reading;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(final Boolean primary) {
        this.primary = primary;
    }

    public Boolean getAcceptedAnswer() {
        return acceptedAnswer;
    }

    public void setAcceptedAnswer(final Boolean acceptedAnswer) {
        this.acceptedAnswer = acceptedAnswer;
    }

    @NonNull
    @Override
    public String toString() {
        return "ReadingEntity{" +
                "readingId=" + readingId +
                ", subjectId=" + readingsOfSubjectId +
                ", type='" + type + '\'' +
                ", reading='" + reading + '\'' +
                ", primary=" + primary +
                ", acceptedAnswer=" + acceptedAnswer +
                '}';
    }
}