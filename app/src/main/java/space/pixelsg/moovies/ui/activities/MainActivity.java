package space.pixelsg.moovies.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import space.pixelsg.moovies.R;
import space.pixelsg.moovies.data.models.Movie;
import space.pixelsg.moovies.databinding.ActivityMainBinding;
import space.pixelsg.moovies.ui.adapters.MovieViewAdapter;
import space.pixelsg.moovies.utils.Data;
import space.pixelsg.moovies.vm.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MovieViewAdapter adapter;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        adapter = new MovieViewAdapter(this::onMovieClick);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        setRecycler();

        // Status Changes
        viewModel.stateLiveData.observe(this, state -> {
            if(state == Data.State.LOADING && binding.recyclerView.getAdapter().getItemCount() == 0)
                binding.progressBar.setVisibility(View.VISIBLE);
            else
                binding.progressBar.setVisibility(View.GONE);
        });

        // SearchBar Submit
        binding.searchView.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearchAction(v);
                return true;
            }
            return false;
        });
    }

    private void onMovieClick(View v, Movie movie) {
        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
        intent.putExtra("id", movie.id);
        startActivity(intent);
    }

    private void onSearchAction(TextView v) {
        adapter.submitList(null);
        viewModel.setQuery(v.getText().toString());
        setRecycler();
        hideKeyboard();
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(getCurrentFocus() != null)
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void setRecycler() {
        binding.recyclerView.setAdapter(adapter);
        viewModel.moviesLiveData.observe(this, adapter::submitList);
    }
}