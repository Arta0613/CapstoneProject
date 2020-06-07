package com.example.capstoneproject.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.capstoneproject.repository.WaniRepository;
import com.example.capstoneproject.ui.DetailViewModel;
import com.example.capstoneproject.ui.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull private final WaniRepository repository;

    public ViewModelFactory(@NonNull final WaniRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository);
        } else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(repository);
        }

        throw new IllegalArgumentException("Unknown ViewModel Class: " + modelClass);
    }
}