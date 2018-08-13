package ru.startandroid.sportnews.models.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DbArticle {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public String publishedAt;

}
