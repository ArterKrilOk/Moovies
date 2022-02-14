package space.pixelsg.moovies.data.models;

import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Movie {
    @SerializedName("id")
    public int id;

    @SerializedName("adult")
    public boolean isAdult;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("genre_ids")
    public List<Integer> genreIDs;

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("overview")
    public String overview;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("popularity")
    public double popularity;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("title")
    public String title;

    @SerializedName("video")
    public boolean isVideo;

    @SerializedName("vote_average")
    public double voteAverage;

    @SerializedName("vote_count")
    public int voteCount;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && isAdult == movie.isAdult && Double.compare(movie.popularity, popularity) == 0 && isVideo == movie.isVideo && Double.compare(movie.voteAverage, voteAverage) == 0 && voteCount == movie.voteCount && Objects.equals(backdropPath, movie.backdropPath) && Objects.equals(genreIDs, movie.genreIDs) && Objects.equals(originalLanguage, movie.originalLanguage) && Objects.equals(originalTitle, movie.originalTitle) && Objects.equals(overview, movie.overview) && Objects.equals(posterPath, movie.posterPath) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isAdult, backdropPath, genreIDs, originalLanguage, originalTitle, overview, posterPath, popularity, releaseDate, title, isVideo, voteAverage, voteCount);
    }
}
