package ru.startandroid.sportnews.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import ru.startandroid.sportnews.models.Converter;
import ru.startandroid.sportnews.models.db.AppDatabase;
import ru.startandroid.sportnews.models.db.ArticleDao;
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


    }
}
