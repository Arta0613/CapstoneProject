package com.example.capstoneproject.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.capstoneproject.database.LevelEntityMappers;
import com.example.capstoneproject.database.entities.LevelEntity;
import com.example.capstoneproject.domain.Level;
import com.example.capstoneproject.domain.SubjectType;
import com.example.capstoneproject.repository.local.WaniLocalDataSource;
import com.example.capstoneproject.repository.network.WaniRemoteDataSource;

import java.util.List;

import io.reactivex.Single;

public class WaniRepository {

    @NonNull private final WaniRemoteDataSource remoteDataSource;
    @NonNull private final WaniLocalDataSource localDataSource;

    @NonNull private final LevelEntityMappers levelEntityMappers = new LevelEntityMappers();

    @Nullable
    private List<Level> waniLevels;
    private SubjectType selectedSubject;

    public WaniRepository(@NonNull final Context context) {
        remoteDataSource = new WaniRemoteDataSource();
        localDataSource = new WaniLocalDataSource(context);
    }

    @NonNull
    public final Single<List<Level>> loadRemoteLevels() {
        return remoteDataSource.getLevels();
    }

    @NonNull
    public final LiveData<List<Level>> loadLocalLevels() {
        return Transformations.map(
                localDataSource.getLevels(),
                levelEntityMappers::mapLevels
        );
    }

    @NonNull
    public final List<LevelEntity> loadLocalLevelsSynchronous() {
        return localDataSource.getLevelsSynchronous();
    }

    @NonNull
    public Single<List<Long>> saveLevelsToDatabase(@NonNull final List<Level> levels) {
        return localDataSource.insertLevels(levelEntityMappers.mapLevelEntities(levels));
    }

    // In memory holder
    @Nullable
    public final List<Level> getWaniLevels() {
        return waniLevels;
    }

    public void setWaniLevels(@NonNull final List<Level> waniLevels) {
        this.waniLevels = waniLevels;
    }

    @Nullable
    public final SubjectType getSelectedSubject() {
        return selectedSubject;
    }

    public void setSelectSubject(@NonNull final SubjectType selectedSubject) {
        this.selectedSubject = selectedSubject;
    }
}