package space.pixelsg.moovies.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails {
    @SerializedName("id")
    public int id;

//    @SerializedName("adult")
//    public boolean isAdult;

    @SerializedName("backdrop_path")
    public String backdropPath;

//    @SerializedName("budget")
//    public int budget;
//
//    @SerializedName("genres")
//    public List<Genre> genres;
//
//    @SerializedName("homepage")
//    public String homepage;
//
//    @SerializedName("imdb_id")
//    public String imdbId;
//
//    @SerializedName("original_language")
//    public String originalLanguage;

    @SerializedName("title")
    public String title;

//    @SerializedName("original_title")
//    public String originalTitle;

    @SerializedName("overview")
    public String overview;

//    @SerializedName("tagline")
//    public String tagline;
//
//    @SerializedName("video")
//    public boolean isVideo;
//
//    @SerializedName("vote_average")
//    public double voteAverage;
//
//    @SerializedName("vote_count")
//    public int voteCount;

    @SerializedName("poster_path")
    public String posterPath;
}
