package com.example.capstoneproject.database;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import com.example.capstoneproject.database.entities.ReadingEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ReadingEntityTypeConverter {

    @NonNull private static final Gson gson = new Gson();
    @NonNull private static final Type listType = new TypeToken<List<ReadingEntity>>() {}.getType();

    @TypeConverter
    public static List<ReadingEntity> stringToReadingEntityList(final String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String readingEntityListToString(final List<ReadingEntity> readingEntityList) {
        return gson.toJson(readingEntityList);
    }
}