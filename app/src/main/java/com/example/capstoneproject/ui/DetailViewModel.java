package com.example.capstoneproject.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.capstoneproject.repository.WaniRepository;
import com.example.capstoneproject.utils.SingleLiveEvent;

public class DetailViewModel extends ViewModel {

    @NonNull private final WaniRepository repository;
    @NonNull public final SingleLiveEvent<String> playAudioEvent = new SingleLiveEvent<>();

    public DetailViewModel(@NonNull final WaniRepository repository) {
        this.repository = repository;
    }

    public void playAudio(@NonNull final String audioUrl) {
        playAudioEvent.setValue(audioUrl);
    }
}