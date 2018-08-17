package ru.startandroid.sportnews.mvp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.startandroid.sportnews.api.ApiClient;
import ru.startandroid.sportnews.models.Converter;
import ru.startandroid.sportnews.models.db.ArticleDao;
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

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Toothpick.inject(this, Toothpick.openScope(APP_SCOPE));
        getViewState().showProgressBar(true);
        loadDataFromApi(1);

        compositeDisposable.add(articleDao.getDbArticle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dbArticles -> {
                    getViewState().showArticles(dbArticles);
                    getViewState().showProgressBar(false);
                    getViewState().enableScrollListener(true);
                }));
    }

    public void loadDataFromApi(int page) {
        getViewState().enableScrollListener(false);
        if (page > 1) {
            getViewState().showBottomProgress(true);
        }
        compositeDisposable.add(apiClient.getArticleList("us", "sports")
                .map(articleList -> converter.convert(articleList))
                .map(dbArtticle -> articleDao.insert(dbArtticle))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(longs -> {
                            getViewState().showSwipeRefresherBar(false);
                            if (page > 1) {
                                getViewState().showBottomProgress(false);
                            }
                        },
                        error -> {
                            getViewState().showError(error.getMessage());
                            getViewState().showSwipeRefresherBar(false);
                            getViewState().showProgressBar(false);
                            if (page > 1) {
                                getViewState().showBottomProgress(false);
                            }
                        }));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
