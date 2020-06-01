package com.example.capstoneproject.data;

import androidx.annotation.NonNull;

import com.example.capstoneproject.data.response.WaniKaniSubjectsApiResponse;
import com.example.capstoneproject.domain.Level;
import com.example.capstoneproject.domain.LevelMapper;
import com.example.capstoneproject.network.RetrofitServiceGenerator;
import com.example.capstoneproject.network.WaniKaniSubjectsApi;

import java.util.List;

import io.reactivex.Single;

public class WaniKaniSubjectsApiUseCase {

    @NonNull private final WaniKaniSubjectsApi waniKaniSubjectsApi =
            RetrofitServiceGenerator.createSubjectsApiService(WaniKaniSubjectsApi.class);

    @NonNull private final LevelMapper mapper = new LevelMapper();

    @NonNull
    public final Single<List<Level>> getSubjects() {
        return waniKaniSubjectsApi.getSubjects()
                .map(WaniKaniSubjectsApiResponse::getData)
                .map(mapper::mapLevels)
                .singleOrError();
    }
}