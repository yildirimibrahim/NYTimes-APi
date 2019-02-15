package com.softtech.nytime.core.webservices;

import com.softtech.nytime.model.NewsResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsProvider {

    @GET("/svc/mostpopular/v2/mostviewed/all-sections/"+ NewsService.PERIOD +".json?api-key=" + NewsService.API_KEY)
    Call<NewsResponseModel> mostPopular();
}