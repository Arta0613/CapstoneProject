package com.example.capstoneproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.capstoneproject.R;
import com.example.capstoneproject.WaniReferenceApplication;
import com.example.capstoneproject.databinding.ActivityMainBinding;
import com.example.capstoneproject.repository.WaniRepository;
import com.example.capstoneproject.utils.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(
                this, new ViewModelFactory(getRepository())).get(MainViewModel.class
        );

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.mainToolbar);

        observeLevels();
        listenToLevelSelector(binding.levelSelector);

        observeSubjectClickedEvent();
    }

    private void observeLevels() {
        viewModel.getLevelsLiveData().observe(this, levels -> {
            if (levels.isEmpty()) { // TODO: Additionally add condition (cache expiry) to update periodically OR check API if data was updated and load accordingly
                viewModel.load();
                return;
            }

            if (getRepository().getWaniLevels() == null) {
                getRepository().setWaniLevels(levels);
            }

            viewModel.setSubjectAdapters();
        });
    }

    private void listenToLevelSelector(@NonNull final AppCompatSpinner levelSelector) {
        levelSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(
                    final AdapterView<?> parent, final View view, final int position, final long id
            ) {
                viewModel.currentLevel = position;
                viewModel.setSubjectAdapters();
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {}
        });
    }

    private void observeSubjectClickedEvent() {
        viewModel.showDetail.observe(this, subjectType -> {
            getRepository().setSelectSubject(subjectType);

            final Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this, (View) binding.getRoot().findViewById(R.id.card_view), "cardview"
            );
            startActivity(intent, options.toBundle());
        });
    }

    @NonNull
    private WaniRepository getRepository() {
        return ((WaniReferenceApplication) getApplication()).getAppContainer().getWaniRepository();
    }
}