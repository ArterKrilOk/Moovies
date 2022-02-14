package space.pixelsg.moovies.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import space.pixelsg.moovies.R;
import space.pixelsg.moovies.data.models.Credits;
import space.pixelsg.moovies.data.models.MovieDetails;
import space.pixelsg.moovies.data.source.remote.RemoteConfig;
import space.pixelsg.moovies.databinding.ActivityMainBinding;
import space.pixelsg.moovies.databinding.ActivityMovieBinding;
import space.pixelsg.moovies.ui.adapters.PersonViewAdapter;
import space.pixelsg.moovies.utils.Data;
import space.pixelsg.moovies.vm.MainActivityViewModel;
import space.pixelsg.moovies.vm.MovieActivityViewModel;

public class MovieActivity extends AppCompatActivity {

    private ActivityMovieBinding binding;
    private MovieActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(MovieActivityViewModel.class);

        viewModel.loadMovie(getIntent().getIntExtra("id", 0));

        viewModel.movie.observe(this, movieDetailsData -> {
            if(movieDetailsData.state == Data.State.LOADING) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.scrollView.setVisibility(View.GONE);
            }
            if(movieDetailsData.state == Data.State.SUCCESS) {
                binding.progressBar.setVisibility(View.GONE);
                binding.scrollView.setVisibility(View.VISIBLE);
                setMovie(movieDetailsData.data);
            }
        });

        viewModel.credits.observe(this, creditsData -> {
            if(creditsData.state == Data.State.LOADING)
                binding.recyclerView.setVisibility(View.GONE);
            if(creditsData.state == Data.State.SUCCESS)
                setCredits(creditsData.data);
        });
    }

    private void setCredits(Credits credits) {
        binding.recyclerView.setVisibility(View.VISIBLE);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        ));
        binding.recyclerView.setAdapter(new PersonViewAdapter(credits.cast));
    }

    private void setMovie(MovieDetails details) {
        binding.posterView.loadImageFull(details.posterPath);
        binding.overviewView.setText(details.overview);

        Glide
                .with(binding.backgroundView)
                .load(RemoteConfig.IMAGE_URL + details.backdropPath)
                .centerCrop()
                .into(binding.backgroundView);
    }
}