package com.example.sociopinia.remote.api;

import com.example.sociopinia.remote.response.SentimentAnalysisResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.example.sociopinia.util.Constants.SENTIMENT_ANALYSIS_API_HOST;
import static com.example.sociopinia.util.Constants.SENTIMENT_ANALYSIS_API_KEY;

public interface SentimentAnalysisApi {

    @Headers({
            "accept: application/json",
            "x-rapidapi-key: " + SENTIMENT_ANALYSIS_API_KEY,
            "x-rapidapi-host: " + SENTIMENT_ANALYSIS_API_HOST,
            "useQueryString: true"
    })
    @GET("/analyze/")
    Call<SentimentAnalysisResponse> analyzeContent(@Query("text") String content);

}
