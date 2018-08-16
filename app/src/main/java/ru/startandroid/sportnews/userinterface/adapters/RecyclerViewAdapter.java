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
import ru.startandroid.sportnews.models.ui.RecyclerAdapterItem;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnArticleClickListener onArticleClickListener;

    private List<RecyclerAdapterItem> recyclerAdapterItemList = new ArrayList<>();

    public interface OnArticleClickListener {
        void onClick(DbArticle dbArticle);
    }

    public void setOnArticleClickListener(OnArticleClickListener onArticleClickListener) {
        this.onArticleClickListener = onArticleClickListener;
    }

    public void setArticleList(List<DbArticle> articleList) {
        recyclerAdapterItemList.clear();
        for (DbArticle dbArticle : articleList) {
            recyclerAdapterItemList.add(new RecyclerAdapterItem(dbArticle, RecyclerAdapterItem.RecyclerAdapterItemType.ARTICLE));
        }

    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle, tvAuthor, tvDescription;

        ArticleViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
        }
    }

    class ProgressBarViewHolder extends RecyclerView.ViewHolder {
        ProgressBarViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (RecyclerAdapterItem.RecyclerAdapterItemType.values()[viewType]) {
            case ARTICLE:
                return new ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false));
            case PROGRESSBAR:
                return new ProgressBarViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progressbar, parent, false));
            default:
                throw new IllegalArgumentException();
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (recyclerAdapterItemList.get(position).type) {
            case ARTICLE:
                ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
                DbArticle dbArticle = (DbArticle) recyclerAdapterItemList.get(position).data;
                articleViewHolder.tvDescription.setText(dbArticle.description);
                articleViewHolder.tvAuthor.setText(dbArticle.author);
                articleViewHolder.tvTitle.setText(dbArticle.title);
                GlideApp
                        .with(holder.itemView.getContext())
                        .load(dbArticle.urlToImage)
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(articleViewHolder.imageView);
                holder.itemView.setOnClickListener(v -> onArticleClickListener.onClick(dbArticle));
                break;

            case PROGRESSBAR:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return recyclerAdapterItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return recyclerAdapterItemList.get(position).type.ordinal();
    }

    public void showBottomProgress(boolean show) {
        if (show) {
            if (recyclerAdapterItemList.get(recyclerAdapterItemList.size() - 1).type != RecyclerAdapterItem.RecyclerAdapterItemType.PROGRESSBAR) {
                recyclerAdapterItemList.add(new RecyclerAdapterItem(null, RecyclerAdapterItem.RecyclerAdapterItemType.PROGRESSBAR));
            }
        } else {
            if (recyclerAdapterItemList.get(recyclerAdapterItemList.size() - 1).type ==
                    RecyclerAdapterItem.RecyclerAdapterItemType.PROGRESSBAR) {
                recyclerAdapterItemList.remove(recyclerAdapterItemList.size() - 1);
            }
        }
        notifyDataSetChanged();
    }
}



