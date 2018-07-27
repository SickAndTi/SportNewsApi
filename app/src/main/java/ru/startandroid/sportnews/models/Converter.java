package ru.startandroid.sportnews.models;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.startandroid.sportnews.models.api.Article;
import ru.startandroid.sportnews.models.db.DbArticle;

public class Converter {
    @Inject
    Converter() {
    }

    public List<DbArticle> convert(List<Article> articleList) {
        List<DbArticle> dbArticleList = new ArrayList<>();
        for (Article article : articleList) {
            DbArticle dbArticle = new DbArticle();
            dbArticle.author = article.author;
            dbArticle.description = article.description;
            dbArticle.publishedAt = article.publishedAt;
            dbArticle.title = article.title;
            dbArticle.url = article.url;
            dbArticle.urlToImage = article.urlToImage;
            dbArticleList.add(dbArticle);
        }
        return dbArticleList;
    }
}
