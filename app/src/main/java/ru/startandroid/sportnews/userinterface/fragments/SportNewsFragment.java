package ru.startandroid.sportnews.userinterface.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import ru.startandroid.sportnews.R;
import ru.startandroid.sportnews.models.db.DbArticle;
import ru.startandroid.sportnews.mvp.SportNewsPresenter;
import ru.startandroid.sportnews.mvp.SportNewsView;
import ru.startandroid.sportnews.userinterface.adapters.RecyclerViewAdapter;
import timber.log.Timber;

public class SportNewsFragment extends MvpAppCompatFragment implements SportNewsView {
    @InjectPresenter
    SportNewsPresenter sportNewsPresenter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sportnews, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        swipeRefreshLayout.setOnRefreshListener();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



    }

    @Override
    public void showArticles(List<DbArticle> dbArticleList) {
        RecyclerViewAdapter adapter = (RecyclerViewAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new RecyclerViewAdapter(dbArticleList);
            recyclerView.setAdapter(adapter);
        }
        adapter.setDbArticleList(dbArticleList);
        recyclerView.getAdapter().notifyDataSetChanged();
    }


    @Override
    public void showError(String errorMessage) {
        Timber.e(errorMessage);
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSwipeRefresherBar(boolean showSwipeRefresherBar) {
        swipeRefreshLayout.setRefreshing(showSwipeRefresherBar);
    }
}






