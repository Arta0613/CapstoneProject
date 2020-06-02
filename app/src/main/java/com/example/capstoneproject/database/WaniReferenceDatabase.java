package com.example.capstoneproject.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.capstoneproject.database.entities.LevelEntity;
import com.example.capstoneproject.database.entities.ReadingEntity;
import com.example.capstoneproject.database.entities.SubjectTypeEntity;

@Database(entities = {LevelEntity.class, SubjectTypeEntity.class, ReadingEntity.class}, version = 1, exportSchema = false)
public abstract class WaniReferenceDatabase extends RoomDatabase {

    private static final String TAG = WaniReferenceDatabase.class.getName();

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "wani_reference_database";

    private static volatile WaniReferenceDatabase INSTANCE;

    public static WaniReferenceDatabase getInstance(@NonNull final Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    Log.d(TAG, "Creating a new WaniReference Database");

                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WaniReferenceDatabase.class,
                            DATABASE_NAME
                    ).build();
                }
            }
        }

        Log.d(TAG, "Getting the database instance");
        return INSTANCE;
    }

    public abstract WaniReferenceDao waniReferenceDao();
}