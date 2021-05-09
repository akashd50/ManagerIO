package com.greymatter.managerio.ui.adapters;

import android.content.Context;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class AListViewAdapter<T> extends BaseAdapter {
    private final Context context;
    private List<T> items;
    public AListViewAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    public void setItems(List<T> list) {
        items.clear();
        items.addAll(list);
        notifyDataSetChanged();
    }

    public void addItems(List<T> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }

    public void addItem(T item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int i) {
        return items.get(i);
    }

    public Context getContext() {
        return context;
    }
}
