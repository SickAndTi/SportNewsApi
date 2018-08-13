package ru.startandroid.sportnews.userinterface.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import ru.startandroid.sportnews.R;
import ru.startandroid.sportnews.models.db.DbArticle;
import ru.startandroid.sportnews.userinterface.adapters.RecyclerViewAdapter;
import ru.startandroid.sportnews.userinterface.fragments.ArticleFragment;
import ru.startandroid.sportnews.userinterface.fragments.SportNewsFragment;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnArticleClickListener {
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        Fragment fragment = new SportNewsFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, fragment);
        ft.commit();
    }

    @Override
    public void onClick(DbArticle dbArticle) {
        Fragment fragment = ArticleFragment.createInstance(dbArticle.url);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
