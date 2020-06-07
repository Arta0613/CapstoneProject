package com.example.capstoneproject.repository.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.capstoneproject.database.WaniReferenceDao;
import com.example.capstoneproject.database.WaniReferenceDatabase;
import com.example.capstoneproject.database.entities.LevelEntity;

import java.util.List;

import io.reactivex.Single;

public class WaniLocalDataSource {

    @NonNull private final WaniReferenceDao dao;

    public WaniLocalDataSource(@NonNull final Context context) {
        dao = WaniReferenceDatabase.getInstance(context).waniReferenceDao();
    }

    @NonNull
    public LiveData<List<LevelEntity>> getLevels() {
        return dao.getLevels();
    }

    @NonNull
    public List<LevelEntity> getLevelsSynchronous() {
        return dao.getLevelsSynchronous();
    }

    @NonNull
    public Single<List<Long>> insertLevels(@NonNull final List<LevelEntity> levelEntities) {
        return dao.insertLevels(levelEntities);
    }
}