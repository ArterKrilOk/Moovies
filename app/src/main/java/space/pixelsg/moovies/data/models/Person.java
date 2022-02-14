package space.pixelsg.moovies.data.models;

import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("profile_path")
    public String profilePath;

    @SerializedName("character")
    public String character;
}
