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
                        childColumns = "pronunciationOfSubjectId",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class PronunciationAudioEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer pronunciationId;

    @ColumnInfo(index = true)
    private Integer pronunciationOfSubjectId;

    private String audioUrl;
    private String pronunciation;
    private String voiceActorName;
    private String gender;
    private String voiceDescription;

    public PronunciationAudioEntity() {
    }

    @Ignore

    public PronunciationAudioEntity(
            @NonNull final Integer pronunciationOfSubjectId,
            @NonNull final String audioUrl,
            @NonNull final String pronunciation,
            @NonNull final String voiceActorName,
            @NonNull final String gender,
            @NonNull final String voiceDescription
    ) {
        this.pronunciationOfSubjectId = pronunciationOfSubjectId;
        this.audioUrl = audioUrl;
        this.pronunciation = pronunciation;
        this.voiceActorName = voiceActorName;
        this.gender = gender;
        this.voiceDescription = voiceDescription;
    }

    public Integer getPronunciationOfSubjectId() {
        return pronunciationOfSubjectId;
    }

    public void setPronunciationOfSubjectId(final Integer pronunciationOfSubjectId) {
        this.pronunciationOfSubjectId = pronunciationOfSubjectId;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(final String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(final String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getVoiceActorName() {
        return voiceActorName;
    }

    public void setVoiceActorName(final String voiceActorName) {
        this.voiceActorName = voiceActorName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public String getVoiceDescription() {
        return voiceDescription;
    }

    public void setVoiceDescription(final String voiceDescription) {
        this.voiceDescription = voiceDescription;
    }

    @NonNull
    @Override
    public String toString() {
        return "PronunciationAudioEntity{" +
                "pronunciationId=" + pronunciationId +
                ", pronunciationOfSubjectId=" + pronunciationOfSubjectId +
                ", audioUrl='" + audioUrl + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", voiceActorName='" + voiceActorName + '\'' +
                ", gender='" + gender + '\'' +
                ", voiceDescription='" + voiceDescription + '\'' +
                '}';
    }
}