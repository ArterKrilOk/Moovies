package space.pixelsg.moovies.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import javax.inject.Inject;

import space.pixelsg.moovies.App;
import space.pixelsg.moovies.data.repository.AppRepository;

public class AppViewModel extends AndroidViewModel {
    @Inject
    public AppRepository appRepository;

    public AppViewModel(@NonNull Application application) {
        super(application);

        App.fromApplication(application).getAppComponent().injectIntoAppViewModel(this);
    }
}
