package space.pixelsg.moovies.data.di;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import space.pixelsg.moovies.data.repository.AppRepository;
import space.pixelsg.moovies.data.source.remote.ApiInterface;
import space.pixelsg.moovies.data.source.remote.RemoteConfig;

@Module
public class DataModule {
    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(RemoteConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    AppRepository provideAppRepository(ApiInterface apiInterface) {
        return new AppRepository(apiInterface);
    }
}
