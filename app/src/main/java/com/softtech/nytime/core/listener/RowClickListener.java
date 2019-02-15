package com.softtech.nytime.core.listener;

public interface RowClickListener<T> {
    void onRowClick(int row, T item);
}