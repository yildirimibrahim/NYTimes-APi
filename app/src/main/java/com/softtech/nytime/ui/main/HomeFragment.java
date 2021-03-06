package com.softtech.nytime.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import com.softtech.nytime.R;
import com.softtech.nytime.core.webservices.NewsService;
import com.softtech.nytime.model.NewsResponseModel;
import com.softtech.nytime.ui.BaseFragment;
import com.softtech.nytime.ui.detail.DetailsActivity;
import com.softtech.nytime.ui.adapter.NewsAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
/**
 * MaxiBilgi
 * Created by SoftTech Garage on 15.02.2019.
 */
public class HomeFragment
        extends BaseFragment {

    private NewsAdapter adapter;
    private RecyclerView rvItems;

    public static Fragment create() {
        return new HomeFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvItems = getView().findViewById(R.id.rvItems);
        sendGetRequest();
    }

    public void search(String str) {
        if (adapter != null) {
            adapter.filter(str);
        }
    }

    private void sendGetRequest() {
        showProgress();
        NewsService.INSTANCE.mostPopularAsync(new Callback<NewsResponseModel>() {
            @Override
            public void onResponse(Call<NewsResponseModel> call, Response<NewsResponseModel> response) {
                dismissProgress();
                adapter = new NewsAdapter(response.body().results, (row, item) -> {
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("item", item);
                    startActivity(intent);
                });
                rvItems.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<NewsResponseModel> call, Throwable t) {
                Timber.e(t);
                dismissProgress();
                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.errorTitle)
                        .setMessage(getString(R.string.errorMessage))
                        .setNegativeButton(R.string.errorNegativeButton, (dialog, which) -> getActivity().finish())
                        .setPositiveButton(R.string.errorPossitiveButton, (dialog, which) -> sendGetRequest())
                        .show();
            }
        });
    }

}