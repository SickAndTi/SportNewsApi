package ru.startandroid.sportnews.mvp;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.startandroid.sportnews.models.db.DbArticle;

public interface SportNewsView extends MvpView {
    void showArticles(List<DbArticle> dbArticleList);

    void showError(String errorMessage);

    void showSwipeRefresherBar(boolean showSwipeRefresherBar);

    void showProgressBar(boolean showProgressBar);
}
