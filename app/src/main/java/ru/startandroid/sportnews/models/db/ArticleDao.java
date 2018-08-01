package ru.startandroid.sportnews.models.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
public interface ArticleDao {
    @Query("SELECT * FROM DbArticle ORDER BY id DESC")
    Flowable<List<DbArticle>> getDbArticle();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(List<DbArticle> dbArticleList);

}
