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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.startandroid.sportnews.R;
import ru.startandroid.sportnews.api.ApiClient;
import ru.startandroid.sportnews.models.api.Article;
import ru.startandroid.sportnews.userinterface.adapters.RecyclerViewAdapter;
import timber.log.Timber;

public class SportNewsFragment extends Fragment {
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sportnews, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ApiClient apiClient = new ApiClient();
        apiClient.getArticleList("us", "sports").enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful()) {
                    Timber.d(String.valueOf(response.body().size()));

                } else {
                    Timber.d(String.valueOf(response.code()));

                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Timber.d(t);
            }
        });
    }
}



