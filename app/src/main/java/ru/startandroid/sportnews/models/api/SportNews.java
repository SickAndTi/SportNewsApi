package ru.startandroid.sportnews.models.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SportNews {

    @SerializedName("status")
    public String status;
    @SerializedName("totalResults")
    public int totalResults;
    @SerializedName("articles")
    public List<Article> articles;

}