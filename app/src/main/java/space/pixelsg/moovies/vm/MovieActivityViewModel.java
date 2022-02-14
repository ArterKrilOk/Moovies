package space.pixelsg.moovies.vm;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import io.reactivex.disposables.CompositeDisposable;
import space.pixelsg.moovies.data.models.Credits;
import space.pixelsg.moovies.data.models.MovieDetails;
import space.pixelsg.moovies.utils.Data;

public class MovieActivityViewModel extends AppViewModel{
    private final CompositeDisposable compositeDisposable;
    public LiveData<Data<MovieDetails>> movie;
    public LiveData<Data<Credits>> credits;

    public MovieActivityViewModel(@NonNull Application application) {
        super(application);
        compositeDisposable = new CompositeDisposable();
    }

    public void loadMovie(int movieId) {
        movie = appRepository.getMovie(movieId, compositeDisposable);
        credits = appRepository.getMovieCredits(movieId, compositeDisposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
