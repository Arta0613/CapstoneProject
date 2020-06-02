package com.example.capstoneproject.database;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import com.example.capstoneproject.database.entities.SubjectTypeEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class SubjectTypeEntityTypeConverter {

    @NonNull private static final Gson gson = new Gson();
    @NonNull private static final Type listType = new TypeToken<List<SubjectTypeEntity>>() {}.getType();

    @TypeConverter
    public static List<SubjectTypeEntity> stringToSubjectTypeEntityList(final String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String subjectTypeEntityListToString(
            final List<SubjectTypeEntity> subjectTypeEntityList) {
        return gson.toJson(subjectTypeEntityList);
    }
}