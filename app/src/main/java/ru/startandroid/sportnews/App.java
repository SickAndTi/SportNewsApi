package ru.startandroid.sportnews;

import android.app.Application;
import android.arch.persistence.room.Room;

import ru.startandroid.sportnews.di.AppModule;
import ru.startandroid.sportnews.models.db.AppDatabase;
import timber.log.Timber;
import toothpick.Toothpick;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        Toothpick.openScope(Constants.APP_SCOPE).installModules(new AppModule(this));
    }

}
