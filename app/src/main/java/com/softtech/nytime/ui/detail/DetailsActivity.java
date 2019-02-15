package com.softtech.nytime.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.softtech.nytime.R;
import com.softtech.nytime.core.utils.NYTimesUtils;
import com.softtech.nytime.model.NewsModel;
import com.softtech.nytime.ui.BaseActivity;

import java.util.Objects;

/**
 * MaxiBilgi
 * Created by SoftTech Garage on 15.02.2019.
 */
public class DetailsActivity extends BaseActivity {

    private NewsModel itemNewModel;
    private Button btnOpenUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initBundle();
        init();
        listener();
    }

    private void initBundle() {
        itemNewModel = (NewsModel) Objects.requireNonNull(getIntent().getExtras()).getSerializable("item");
    }

    private void init() {
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvAuthor = findViewById(R.id.tvAuthor);
        TextView tvSourc = findViewById(R.id.tvSourc);
        TextView tvAbstract = findViewById(R.id.tvAbstract);
        btnOpenUrl = findViewById(R.id.btnOpenUrl);
        ImageView ivItem = findViewById(R.id.ivItem);

        tvTitle.setText(itemNewModel.title);
        tvAuthor.setText(itemNewModel.byline);
        tvSourc.setText(itemNewModel.source);
        tvAbstract.setText(itemNewModel.abs_abstract);
        Glide.with(getApplicationContext()).load(itemNewModel.getImageUrl()).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.image_place_holder)).into(ivItem);
    }

    private void listener() {
        btnOpenUrl.setOnClickListener(v -> {
            Intent intent = NYTimesUtils.openBrowser(itemNewModel.url);
            if (intent != null) {
                startActivity(intent);
            }
        });
    }
}
