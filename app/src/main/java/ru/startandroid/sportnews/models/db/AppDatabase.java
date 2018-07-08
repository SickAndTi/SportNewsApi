package ru.startandroid.sportnews.models.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DbArticle.class}, version = 2)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract ArticleDao articleDao();
    }
