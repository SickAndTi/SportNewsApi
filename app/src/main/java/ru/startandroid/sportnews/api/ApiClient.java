package ru.startandroid.sportnews.api;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.startandroid.sportnews.Constants;
import ru.startandroid.sportnews.models.api.SportNews;

public class ApiClient {
    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build();

    private SportNewsApi sportNewsApi = retrofit.create(SportNewsApi.class);

    public Observable<SportNews> getArticleList(String country, String category) {
        return sportNewsApi.getArticleList(country, category, Constants.APIKEY);
    }
}

