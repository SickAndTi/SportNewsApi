package ru.startandroid.sportnews.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.startandroid.sportnews.api.ApiClient;
import ru.startandroid.sportnews.models.Converter;
import ru.startandroid.sportnews.models.db.AppDatabase;
import ru.startandroid.sportnews.models.db.ArticleDao;
import timber.log.Timber;
import toothpick.config.Module;

public class AppModule extends Module {
    public AppModule(Context context) {
        AppDatabase appDatabase;
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .build();
        bind(AppDatabase.class).toInstance(appDatabase);
        bind(ArticleDao.class).toInstance(appDatabase.articleDao());
        bind(Converter.class).singletonInScope();
        bind(ApiClient.class).singletonInScope();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(log -> Timber.d(log)).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        bind(Retrofit.class).toInstance(new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build());


    }
}
