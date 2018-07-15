package ru.startandroid.sportnews.models.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
public interface ArticleDao {
    @Query("SELECT * FROM DbArticle ORDER BY id DESC")
    Flowable<List<DbArticle>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(List<DbArticle> dbArticleList);

    @Update
    int update(Observable<DbArticle> dbArticleList);

    @Delete
    void delete(Observable<DbArticle> dbArticle);

}
