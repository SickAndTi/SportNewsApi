package ru.startandroid.sportnews;

import android.app.Application;
import android.arch.persistence.room.Room;

import ru.startandroid.sportnews.models.db.AppDatabase;
import timber.log.Timber;

public class App extends Application {
    public static App app;

    private AppDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        app = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();
    }

    public static App getApp() {
        return app;
    }

    public static void setApp(App app) {
        App.app = app;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }
}
