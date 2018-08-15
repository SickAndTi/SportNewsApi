package ru.startandroid.sportnews.userinterface.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.startandroid.sportnews.GlideApp;
import ru.startandroid.sportnews.R;
import ru.startandroid.sportnews.models.db.DbArticle;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private OnArticleClickListener onArticleClickListener;

    public void setOnArticleClickListener(OnArticleClickListener onArticleClickListener) {
        this.onArticleClickListener = onArticleClickListener;
    }

    private List<DbArticle> dbArticleList = new ArrayList<>();

    public interface OnArticleClickListener {
        void onClick(DbArticle dbArticle);
    }

    public void setDbArticleList(List<DbArticle> dbArticleList) {
        this.dbArticleList = dbArticleList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle, tvAuthor, tvDescription;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DbArticle dbArticle = dbArticleList.get(position);
        holder.tvDescription.setText(dbArticle.description);
        holder.tvAuthor.setText(dbArticle.author);
        holder.tvTitle.setText(dbArticle.title);
        GlideApp
                .with(holder.itemView.getContext())
                .load(dbArticle.urlToImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onArticleClickListener.onClick(dbArticle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dbArticleList.size();
    }
}



