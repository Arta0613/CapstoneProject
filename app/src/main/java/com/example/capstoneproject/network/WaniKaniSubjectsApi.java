package com.example.capstoneproject.network;

import com.example.capstoneproject.data.response.WaniKaniSubjectsApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WaniKaniSubjectsApi {

    @GET("subjects/?levels=1,2,3")
    Observable<WaniKaniSubjectsApiResponse> getSubjects();
}