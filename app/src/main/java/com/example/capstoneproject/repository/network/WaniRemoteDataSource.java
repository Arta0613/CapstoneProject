package com.example.capstoneproject.repository.network;

import androidx.annotation.NonNull;

import com.example.capstoneproject.data.WaniKaniSubjectsApiUseCase;
import com.example.capstoneproject.domain.Level;

import java.util.List;

import io.reactivex.Single;

public class WaniRemoteDataSource {

    @NonNull
    private final WaniKaniSubjectsApiUseCase waniKaniSubjectsApiUseCase =
            new WaniKaniSubjectsApiUseCase();

    @NonNull
    public final Single<List<Level>> getLevels() {
        return waniKaniSubjectsApiUseCase.getSubjects();
    }
}