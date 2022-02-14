package space.pixelsg.moovies;

import android.app.Application;

import com.google.android.material.color.DynamicColors;

import space.pixelsg.moovies.data.di.DataModule;
import space.pixelsg.moovies.utils.AppComponent;
import space.pixelsg.moovies.utils.DaggerAppComponent;

public class App  extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        DynamicColors.applyToActivitiesIfAvailable(this);

        appComponent = DaggerAppComponent
                .builder()
                .dataModule(new DataModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static App fromApplication(Application application) {
        return (App) application;
    }
}
