package space.pixelsg.moovies.data.models;

import androidx.annotation.NonNull;

import space.pixelsg.moovies.data.source.remote.RemoteConfig;

public class MovieRequest {
    public String query;
    public String language;

    public MovieRequest() {
        language = RemoteConfig.DEFAULT_LANGUAGE;
        query = "";
    }

    public MovieRequest(@NonNull String query) {
        this.query = query;
        language = RemoteConfig.DEFAULT_LANGUAGE;
    }
}
