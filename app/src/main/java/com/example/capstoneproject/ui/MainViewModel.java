package com.example.capstoneproject.ui;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.capstoneproject.domain.Level;
import com.example.capstoneproject.repository.WaniRepository;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    @NonNull private static final String TAG = MainViewModel.class.getName();

    @NonNull private final WaniRepository repository;
    @NonNull private final LiveData<List<Level>> levelsLiveData;
    @NonNull private final CompositeDisposable disposable = new CompositeDisposable();

    @NonNull private final SubjectAdapter radicalsAdapter = new SubjectAdapter();
    @NonNull private final SubjectAdapter kanjiAdapter = new SubjectAdapter();
    @NonNull private final SubjectAdapter vocabularyAdapter = new SubjectAdapter();

    public int currentLevel = 0;
    private int previousLevel = 0;

    public MainViewModel(@NonNull final WaniRepository repository) {
        this.repository = repository;
        levelsLiveData = repository.loadLocalLevels();
    }

    @NonNull
    public LiveData<List<Level>> getLevelsLiveData() {
        return levelsLiveData;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }

    @NonNull
    public final SubjectAdapter getRadicalsAdapter() {
        return radicalsAdapter;
    }

    @NonNull
    public final SubjectAdapter getKanjiAdapter() {
        return kanjiAdapter;
    }

    @NonNull
    public final SubjectAdapter getVocabularyAdapter() {
        return vocabularyAdapter;
    }

    public void setSubjectAdapters() {
        if (repository.getWaniLevels() == null || (currentLevel == previousLevel && radicalsAdapter.getItemCount() != 0)) {
            return;
        }

        radicalsAdapter.setSubjectList(Objects.requireNonNull(repository.getWaniLevels()).get(currentLevel).getRadicalList());
        kanjiAdapter.setSubjectList(Objects.requireNonNull(repository.getWaniLevels()).get(currentLevel).getKanjiList());
        vocabularyAdapter.setSubjectList(Objects.requireNonNull(repository.getWaniLevels()).get(currentLevel).getVocabularyList());

        radicalsAdapter.notifyDataSetChanged();
        kanjiAdapter.notifyDataSetChanged();
        vocabularyAdapter.notifyDataSetChanged();

        previousLevel = currentLevel;
    }

    public void load() {
        disposable.add(repository.loadRemoteLevels()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Level>>() {
                    @Override
                    public void onSuccess(final List<Level> levels) {
                        saveLevels(levels);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Log.e(TAG, "onError: retrieving remote data", e);
                    }
                }));
    }

    private void saveLevels(@NonNull final List<Level> levels) {
        repository.setWaniLevels(levels);
        disposable.add(repository.saveLevelsToDatabase(levels)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Long>>() {
                    @Override
                    public void onSuccess(final List<Long> longs) {
                        Log.d(TAG, "onSuccess: saved levels to database: " + longs.size());
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Log.e(TAG, "onError: saving levels to database", e);
                    }
                }));
    }
}