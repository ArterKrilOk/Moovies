package space.pixelsg.moovies.data.repository;


import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import space.pixelsg.moovies.data.models.Credits;
import space.pixelsg.moovies.data.models.Movie;
import space.pixelsg.moovies.data.models.MovieDetails;
import space.pixelsg.moovies.data.models.MovieRequest;
import space.pixelsg.moovies.data.models.PagedResults;
import space.pixelsg.moovies.data.source.remote.ApiInterface;
import space.pixelsg.moovies.data.source.remote.RemoteConfig;
import space.pixelsg.moovies.utils.Data;

public class AppRepository {
    private final ApiInterface apiInterface;

    public AppRepository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Observable<PagedResults<Movie>> getMovies(MovieRequest movieRequest, int page) {
        if(movieRequest.query.isEmpty())
            return apiInterface.popularMovies(page, movieRequest.language, RemoteConfig.API_KEY);
        return apiInterface.searchMovies(page, movieRequest.query, movieRequest.language, RemoteConfig.API_KEY);
    }

    @SuppressLint("CheckResult")
    public LiveData<Data<MovieDetails>> getMovie(int movieId, CompositeDisposable compositeDisposable) {
        MutableLiveData<Data<MovieDetails>> movieLiveData = new MutableLiveData<>();

        apiInterface.getMovie(movieId, RemoteConfig.DEFAULT_LANGUAGE, RemoteConfig.API_KEY)
                .doOnSubscribe(disposable -> {
                    compositeDisposable.add(disposable);
                    movieLiveData.postValue(Data.loading());
                })
                .subscribe((MovieDetails result) -> {
                    movieLiveData.postValue(Data.success(result));
                }, throwable -> {
                    movieLiveData.postValue(Data.error(throwable));
                });

        return movieLiveData;
    }

    @SuppressLint("CheckResult")
    public LiveData<Data<Credits>> getMovieCredits(int movieId, CompositeDisposable compositeDisposable) {
        MutableLiveData<Data<Credits>> creditsLiveData = new MutableLiveData<>();

        apiInterface.getMovieCredits(movieId, RemoteConfig.DEFAULT_LANGUAGE, RemoteConfig.API_KEY)
                .doOnSubscribe(disposable -> {
                    compositeDisposable.add(disposable);
                    creditsLiveData.postValue(Data.loading());
                })
                .subscribe((Credits result) -> {
                    creditsLiveData.postValue(Data.success(result));
                }, throwable -> {
                    creditsLiveData.postValue(Data.error(throwable));
                });

        return creditsLiveData;
    }
}
