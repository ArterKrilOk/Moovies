package space.pixelsg.moovies.data.source;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import io.reactivex.disposables.CompositeDisposable;
import space.pixelsg.moovies.data.models.Movie;
import space.pixelsg.moovies.data.models.MovieRequest;
import space.pixelsg.moovies.data.repository.AppRepository;

public class MoviesDataSourceFactory extends DataSource.Factory<Integer, Movie>{
    private final AppRepository appRepository;
    private final CompositeDisposable compositeDisposable;
    private final MutableLiveData<MoviesDataSource> mutableLiveData;
    private final MovieRequest movieRequest;

    public MoviesDataSourceFactory(
            AppRepository appRepository,
            MovieRequest movieRequest,
            CompositeDisposable compositeDisposable
    ) {
        this.appRepository = appRepository;
        this.compositeDisposable = compositeDisposable;
        this.movieRequest = movieRequest;
        this.mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<MoviesDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        MoviesDataSource moviesDataSource = new MoviesDataSource(appRepository, movieRequest, compositeDisposable);
        mutableLiveData.postValue(moviesDataSource);
        return moviesDataSource;
    }
}
