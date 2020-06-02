package com.example.capstoneproject.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.capstoneproject.R;
import com.example.capstoneproject.WaniReferenceApplication;
import com.example.capstoneproject.databinding.ActivityMainBinding;
import com.example.capstoneproject.repository.WaniRepository;
import com.example.capstoneproject.utils.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(
                this, new ViewModelFactory(getRepository())).get(MainViewModel.class
        );

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        observeLevels();
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
        });
    }

    @NonNull
    private WaniRepository getRepository() {
        return ((WaniReferenceApplication) getApplication()).getAppContainer().getWaniRepository();
    }
}