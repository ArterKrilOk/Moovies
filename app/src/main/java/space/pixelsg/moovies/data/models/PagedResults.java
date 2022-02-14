package space.pixelsg.moovies.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PagedResults<T> {
    @SerializedName("page")
    public int page;

    @SerializedName("results")
    public List<T> results;

    @SerializedName("total_pages")
    public int totalPages;

    @SerializedName("total_results")
    public int totalResults;
}
