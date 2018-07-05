package ru.startandroid.sportnews.api;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.startandroid.sportnews.Constants;
import ru.startandroid.sportnews.models.api.Article;
import ru.startandroid.sportnews.models.api.SportNews;

public class ApiClient {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();


    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();


    SportNewsApi sportNewsApi = retrofit.create(SportNewsApi.class);

    public Call<SportNews> getArticleList(String country, String category) {
        return sportNewsApi.getArticleList(country, category, Constants.APIKEY);
    }

}

