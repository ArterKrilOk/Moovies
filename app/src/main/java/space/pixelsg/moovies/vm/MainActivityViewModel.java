package space.pixelsg.moovies.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import io.reactivex.disposables.CompositeDisposable;
import space.pixelsg.moovies.data.models.Movie;
import space.pixelsg.moovies.data.models.MovieRequest;
import space.pixelsg.moovies.data.source.MoviesDataSource;
import space.pixelsg.moovies.data.source.MoviesDataSourceFactory;
import space.pixelsg.moovies.utils.Data;

public class MainActivityViewModel extends AppViewModel {

    private MoviesDataSourceFactory moviesDataSourceFactory;
    private final CompositeDisposable compositeDisposable;

    public LiveData<PagedList<Movie>> moviesLiveData = new MutableLiveData<>();
    public LiveData<Data.State> stateLiveData = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        compositeDisposable = new CompositeDisposable();
        moviesDataSourceFactory = new MoviesDataSourceFactory(appRepository, new MovieRequest(), compositeDisposable);

        createList();

        stateLiveData = Transformations.switchMap(
                moviesDataSourceFactory.getMutableLiveData(),
                MoviesDataSource::getStateMutableLiveData
        );
    }

    public void setQuery(String query) {
        moviesDataSourceFactory = new MoviesDataSourceFactory(appRepository, new MovieRequest(query), compositeDisposable);
        createList();
    }

    private void createList() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPageSize(20)
                .build();

        moviesLiveData = new LivePagedListBuilder<>(moviesDataSourceFactory, config).build();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
