package com.example.capstoneproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.capstoneproject.database.entities.LevelEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface WaniReferenceDao {

    @Transaction
    @Query("SELECT * FROM LevelEntity")
    LiveData<List<LevelEntity>> getLevels();

    @Transaction
    @Insert(entity = LevelEntity.class, onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertLevels(final List<LevelEntity> levelEntities);
}