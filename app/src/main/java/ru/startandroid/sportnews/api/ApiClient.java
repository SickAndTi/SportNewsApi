package ru.startandroid.sportnews.api;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.startandroid.sportnews.Constants;
import ru.startandroid.sportnews.models.api.Article;
import ru.startandroid.sportnews.models.api.SportNews;

public class ApiClient {
    SportNewsApi sportNewsApi;

    @Inject
    public ApiClient(Retrofit retrofit) {
        sportNewsApi = retrofit.create(SportNewsApi.class);
    }

    public Observable<List<Article>> getArticleList(String country, String category) {
        return sportNewsApi.getArticleList(country, category, Constants.APIKEY)
                .map(sportNews -> sportNews.articles);
    }
}

