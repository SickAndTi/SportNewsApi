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
import ru.startandroid.sportnews.models.api.Article;

@Dao
 public abstract class ArticleDao {
    @Query("SELECT * FROM DbArticle ORDER BY id DESC")
    Flowable<List<DbArticle>> getDbArticle() {
        return null;
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public List<Article> insert(List<DbArticle> dbArticleList) {
        return null;
    }

    @Update
    int update(Observable<DbArticle> dbArticleList) {
        return 0;
    }

    @Delete
    void delete(Observable<DbArticle> dbArticle) {

    }

}
