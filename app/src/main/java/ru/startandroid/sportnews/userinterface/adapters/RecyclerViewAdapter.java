package ru.startandroid.sportnews.userinterface.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ru.startandroid.sportnews.R;
import ru.startandroid.sportnews.models.api.Article;
import timber.log.Timber;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<Article> articleList;


    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle, tvSource, tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSource = itemView.findViewById(R.id.tvSource);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.tvDescription.setText(article.description);
        holder.tvSource.setText((CharSequence) article.source);
        holder.tvTitle.setText(article.title);
        Glide.with(holder.itemView.getContext()).load(article.urlToImage).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if (articleList == null) {
            Timber.d("articleList==null");
            return getItemCount();
        } else return articleList.size();

    }
}



