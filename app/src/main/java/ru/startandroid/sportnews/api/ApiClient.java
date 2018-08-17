package ru.startandroid.sportnews.api;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import ru.startandroid.sportnews.Constants;
import ru.startandroid.sportnews.models.api.Article;

public class ApiClient {
    private SportNewsApi sportNewsApi;

    @Inject
    ApiClient(Retrofit retrofit) {
        sportNewsApi = retrofit.create(SportNewsApi.class);
    }

    public Observable<List<Article>> getArticleList(String country, String category) {
        return sportNewsApi.getArticleList(country, category, Constants.APIKEY)
                .map(sportNews -> sportNews.articles);
    }
}

