package ru.startandroid.sportnews.mvp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.startandroid.sportnews.api.ApiClient;
import ru.startandroid.sportnews.models.Converter;
import ru.startandroid.sportnews.models.api.Article;
import ru.startandroid.sportnews.models.db.ArticleDao;
import ru.startandroid.sportnews.models.db.DbArticle;
import timber.log.Timber;
import toothpick.Toothpick;

import static ru.startandroid.sportnews.Constants.APP_SCOPE;

@InjectViewState
public class SportNewsPresenter extends MvpPresenter<SportNewsView> {
    @Inject
    ApiClient apiClient;
    @Inject
    ArticleDao articleDao;
    @Inject
    Converter converter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Toothpick.inject(this, Toothpick.openScope(APP_SCOPE));

        Observable<List<DbArticle>> observable = apiClient.getArticleList("us", "sports")
                .map(articleList -> converter.convert(articleList)
                        .map(dbArticles -> articleDao.insert(dbArticles))
                        .subscribeOn(Schedulers.io()))
                .observeOn(AndroidSchedulers.mainThread());


        compositeDisposable.add(articleDao.getDbArticle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dbArticles -> {
                    Timber.d("%s", dbArticles);
                    getViewState().showArticles((List<DbArticle>) dbArticles);
                }));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
