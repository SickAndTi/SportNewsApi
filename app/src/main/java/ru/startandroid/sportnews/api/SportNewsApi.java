package ru.startandroid.sportnews.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.startandroid.sportnews.models.api.SportNews;

public interface SportNewsApi {
    @GET("top-headlines")
    Call<SportNews> getArticleList(@Query("country") String country, @Query("category") String category, @Query("apikey") String apiKey);
}
