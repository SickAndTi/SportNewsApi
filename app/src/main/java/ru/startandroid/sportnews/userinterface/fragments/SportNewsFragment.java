package ru.startandroid.sportnews.userinterface.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.startandroid.sportnews.R;
import ru.startandroid.sportnews.api.ApiClient;
import ru.startandroid.sportnews.models.Converter;
import ru.startandroid.sportnews.models.db.ArticleDao;
import ru.startandroid.sportnews.userinterface.adapters.RecyclerViewAdapter;
import timber.log.Timber;

public class SportNewsFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Converter converter;
    ArticleDao articleDao;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sportnews, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ApiClient apiClient = new ApiClient();
        Timber.d("onViewCreated");
        apiClient.getArticleList("us", "sports")
                .map(sportNews -> sportNews.articles)
                .map(articleList -> converter.convert(articleList))
                .map(dbArticles -> articleDao.insert(dbArticles))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext (don->Timber.d("DbArticle inserted"));


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}

//    public void showError(String errorMessage) {
//        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
//        Timber.d(errorMessage);
//    }


//        apiClient.getArticleList("us", "sports").enqueue(new Callback<SportNews>() {
//            @Override
//            public void onResponse(Call<SportNews> call, Response<SportNews> response) {
//                if (response.isSuccessful()) {
//                    SportNews responseBody = response.body();
//                    if (responseBody != null) {
//                        List<Article> articlesFromApi = responseBody.articles;
//
//
//                        adapter.setArticleList(articlesFromApi);
//                        adapter.notifyDataSetChanged();
//                    } else {
//                        showError("responseBody == null ");
//                    }
//                } else {
//                    showError("no Response " + response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SportNews> call, Throwable t) {
//                showError(t.getMessage());
//            }
//        });
//    }






