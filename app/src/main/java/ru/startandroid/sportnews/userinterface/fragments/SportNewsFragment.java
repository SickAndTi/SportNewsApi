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
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.startandroid.sportnews.R;
import ru.startandroid.sportnews.api.ApiClient;
import ru.startandroid.sportnews.models.Converter;
import ru.startandroid.sportnews.models.api.Article;
import ru.startandroid.sportnews.models.api.SportNews;
import ru.startandroid.sportnews.userinterface.adapters.RecyclerViewAdapter;
import timber.log.Timber;

public class SportNewsFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    public void showError(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
        Timber.d(errorMessage);
    }

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
        apiClient.getArticleList("us", "sports").enqueue(new Callback<SportNews>() {
            @Override
            public void onResponse(Call<SportNews> call, Response<SportNews> response) {
                if (response.isSuccessful()) {
                    SportNews responseBody = response.body();
                    if (responseBody != null) {
                        List<Article> articlesFromApi = responseBody.articles;

                        adapter.setArticleList(articlesFromApi);
                        adapter.notifyDataSetChanged();
                    } else {
                        showError("responseBody == null ");
                    }
                } else {
                    showError("no Response " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SportNews> call, Throwable t) {
                showError(t.getMessage());
            }
        });
    }
}




