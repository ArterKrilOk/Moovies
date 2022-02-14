package space.pixelsg.moovies.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Credits {
    @SerializedName("id")
    public int id;

    @SerializedName("cast")
    public List<Person> cast;
}
