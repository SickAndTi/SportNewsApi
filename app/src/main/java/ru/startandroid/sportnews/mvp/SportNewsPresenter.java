package ru.startandroid.sportnews.mvp;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.startandroid.sportnews.api.ApiClient;
import ru.startandroid.sportnews.models.Converter;
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
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Toothpick.inject(this, Toothpick.openScope(APP_SCOPE));

        apiClient.getArticleList("us", "sports")
                .map(articleList -> converter.convert(articleList))
                .map(dbArtticle -> articleDao.insert(dbArtticle))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();


        compositeDisposable.add(articleDao.getDbArticle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dbArticles -> {
                    getViewState().showArticles(dbArticles);
                }));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
