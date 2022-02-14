package space.pixelsg.moovies.utils;

import javax.inject.Singleton;

import dagger.Component;
import space.pixelsg.moovies.data.di.DataModule;
import space.pixelsg.moovies.data.repository.AppRepository;
import space.pixelsg.moovies.vm.AppViewModel;

@Component(modules = {DataModule.class})
@Singleton
public interface AppComponent {
    AppRepository getAppRepository();

    void injectIntoAppViewModel(AppViewModel appViewModel);
}
