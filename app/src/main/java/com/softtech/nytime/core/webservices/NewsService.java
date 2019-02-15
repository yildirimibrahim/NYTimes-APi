package com.softtech.nytime.core.webservices;

import com.softtech.nytime.BuildConfig;
import com.softtech.nytime.NYTimesApp;
import com.softtech.nytime.model.NewsResponseModel;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsService {

    public static final NewsService INSTANCE = new NewsService();

    public static final String URL = "https://api.nytimes.com/";
    public static final String API_KEY = "ZRVqhQ6Lf6UuAtkzOqyEnGALnTFp3yhB";
    public static final int PERIOD = 7; // cloud be 1, 7, 30
    private static final String CACHE_PATH = NYTimesApp.getContext().getCacheDir().getAbsolutePath();
    private static final long CACHE_SIZE_IN_MB = 10 * 1024 * 1024;

    private NewsService() {
        // no instances
    }

    public void mostPopularAsync(Callback<NewsResponseModel> callback) {
        getRetrofit().mostPopular().enqueue(callback);
    }

    private NewsProvider getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(NewsProvider.class);
    }

    private OkHttpClient getClient() {
        HttpLoggingInterceptor.Level logLevel = BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(logLevel))
                .cache(new Cache(new File(CACHE_PATH), CACHE_SIZE_IN_MB))
                .build();
    }

}