package ru.startandroid.sportnews.models.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.RoomDatabase;

@Database(version = 2, entities = {DbArticle.class}, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    abstract public ArticleDao articleDao();
}
