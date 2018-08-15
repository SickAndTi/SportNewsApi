package ru.startandroid.sportnews.models.ui;

public class RecyclerAdapterItem {
    public enum RecyclerAdapterItemType {ARTICLE, PROGRESSBAR}

    public Object data;
    public RecyclerAdapterItemType type;

    public RecyclerAdapterItem(Object data, RecyclerAdapterItemType type) {
        this.data = data;
        this.type = type;
    }
}
