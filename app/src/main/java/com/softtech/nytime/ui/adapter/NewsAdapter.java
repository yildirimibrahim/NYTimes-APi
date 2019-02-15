package com.softtech.nytime.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.softtech.nytime.R;
import com.softtech.nytime.core.listener.RowClickListener;
import com.softtech.nytime.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * MaxiBilgi
 * Created by SoftTech Garage on 15.02.2019.
 */
public class NewsAdapter
        extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    public static final int MIN_SEARCH_LEN = 1;

    protected final List<NewsModel> items;
    protected final List<NewsModel> allItems;
    protected final RowClickListener<NewsModel> rowClickListener;

    public NewsAdapter(List<NewsModel> items, RowClickListener<NewsModel> rowClickListener) {
        this.items = items;
        allItems = new ArrayList<>(items);
        this.rowClickListener = rowClickListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_news, viewGroup, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, final int i) {
        final NewsModel item = items.get(i);
        holder.tvTitle.setText(item.title);
        holder.tvAuthor.setText(item.byline);
        holder.tvDate.setText(item.published_date);

        //ColorDrawable colorDrawable = new ColorDrawable(NYTimesUtils.getColor(R.color.colorPrimary));
        Glide.with(holder.root.getContext()).load(item.getImageUrl())
                .apply(RequestOptions
                        .circleCropTransform()
                        .placeholder(R.drawable.image_place_holder))
                .into(holder.ivItem);

        holder.root.setOnClickListener(v -> {
            if (rowClickListener != null)
                rowClickListener.onRowClick(i, item);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(String str) {
        List<NewsModel> filteredItems = new ArrayList<>();
        if (str.length() < MIN_SEARCH_LEN) {
            filteredItems.addAll(allItems);
        } else {
            for (NewsModel item : allItems) {
                if (item.title.toLowerCase().contains(str.toLowerCase())) {
                    filteredItems.add(item);
                }
            }
        }
        items.clear();
        items.addAll(filteredItems);
        notifyDataSetChanged();
    }

    public class NewsViewHolder
            extends RecyclerView.ViewHolder {

        private final View root;
        private final TextView tvTitle;
        private final TextView tvAuthor;
        private final TextView tvDate;
        private final ImageView ivItem;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivItem = itemView.findViewById(R.id.ivItem);
        }

    }

}
