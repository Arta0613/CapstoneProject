package com.example.capstoneproject.ui;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.capstoneproject.R;
import com.example.capstoneproject.WaniReferenceApplication;
import com.example.capstoneproject.databinding.ActivityDetailBinding;
import com.example.capstoneproject.repository.WaniRepository;
import com.example.capstoneproject.utils.NetworkState;
import com.example.capstoneproject.utils.ViewModelFactory;

import java.io.IOException;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityDetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_detail);

        final DetailViewModel viewModel = new ViewModelProvider(
                this, new ViewModelFactory(getRepository())).get(DetailViewModel.class
        );

        binding.setLifecycleOwner(this);
        binding.setSubject(getRepository().getSelectedSubject());
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.detailToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel.playAudioEvent.observe(this, this::playPronunciation);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            supportFinishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build());
    }

    @Override
    protected void onStop() {
        mediaPlayer.release();
        mediaPlayer = null;
        super.onStop();
    }

    public void playPronunciation(@NonNull final String audioUrl) {
        if (noNetwork()) {
            Toast.makeText(this, R.string.no_connection_when_playing_audio, Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Toast.makeText(this, R.string.could_not_play_audio, Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    private WaniRepository getRepository() {
        return ((WaniReferenceApplication) getApplication()).getAppContainer().getWaniRepository();
    }

    private boolean noNetwork() {
        return !NetworkState.isConnectedToNetwork(DetailActivity.this);
    }
}