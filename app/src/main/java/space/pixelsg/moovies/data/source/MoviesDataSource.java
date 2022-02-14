package space.pixelsg.moovies.data.source;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import io.reactivex.disposables.CompositeDisposable;
import space.pixelsg.moovies.data.models.Movie;
import space.pixelsg.moovies.data.models.MovieRequest;
import space.pixelsg.moovies.data.models.PagedResults;
import space.pixelsg.moovies.data.repository.AppRepository;
import space.pixelsg.moovies.utils.Data;

public class MoviesDataSource extends PageKeyedDataSource<Integer, Movie> {
    private final AppRepository appRepository;
    private final CompositeDisposable compositeDisposable;
    private final MutableLiveData<Data.State> stateMutableLiveData;

    private final MovieRequest movieRequest;
    private int index;

    public MoviesDataSource(AppRepository appRepository, MovieRequest movieRequest, CompositeDisposable compositeDisposable) {
        this.appRepository = appRepository;
        this.compositeDisposable = compositeDisposable;
        stateMutableLiveData = new MutableLiveData<>();
        this.movieRequest = movieRequest;
        index = 1;
    }


    public MutableLiveData<Data.State> getStateMutableLiveData() {
        return stateMutableLiveData;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadInitial(
            @NonNull LoadInitialParams<Integer> params,
            @NonNull LoadInitialCallback<Integer, Movie> callback
    ) {
        appRepository.getMovies(movieRequest, index)
                .doOnSubscribe(disposable -> {
                    compositeDisposable.add(disposable);
                    stateMutableLiveData.postValue(Data.State.LOADING);
                })
                .subscribe((PagedResults<Movie> result) -> {
                    stateMutableLiveData.postValue(Data.State.SUCCESS);
                    index++;
                    callback.onResult(result.results, null, index);
                }, throwable -> {
                    stateMutableLiveData.postValue(Data.State.ERROR);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadAfter(
            @NonNull LoadParams<Integer> params,
            @NonNull LoadCallback<Integer, Movie> callback
    ) {
        appRepository.getMovies(movieRequest, params.key)
                .doOnSubscribe(disposable -> {
                    compositeDisposable.add(disposable);
                    stateMutableLiveData.postValue(Data.State.LOADING);
                })
                .subscribe((PagedResults<Movie> result) -> {
                    stateMutableLiveData.postValue(Data.State.SUCCESS);
                    callback.onResult(result.results, params.key + 1);
                }, throwable -> {
                    stateMutableLiveData.postValue(Data.State.ERROR);
                });
    }

    @Override
    public void loadBefore(
            @NonNull LoadParams<Integer> params,
            @NonNull LoadCallback<Integer, Movie> callback
    ) { }
}
