package space.pixelsg.moovies.data.source.remote;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import space.pixelsg.moovies.data.models.Credits;
import space.pixelsg.moovies.data.models.Movie;
import space.pixelsg.moovies.data.models.MovieDetails;
import space.pixelsg.moovies.data.models.PagedResults;

public interface ApiInterface {

    @GET("movie/popular")
    Observable<PagedResults<Movie>> popularMovies(
        @Query("page") int page,
        @Query("language") String language,
        @Query("api_key") String apiKey
    );

    @GET("search/movie")
    Observable<PagedResults<Movie>> searchMovies(
            @Query("page") int page,
            @Query("query") String query,
            @Query("language") String language,
            @Query("api_key") String apiKey
    );

    @GET("movie/{id}")
    Observable<MovieDetails> getMovie(
            @Path(value = "id", encoded = false) int movieId,
            @Query("language") String language,
            @Query("api_key") String apiKey
    );

    @GET("movie/{id}/credits")
    Observable<Credits> getMovieCredits(
            @Path(value = "id", encoded = false) int movieId,
            @Query("language") String language,
            @Query("api_key") String apiKey
    );
}
