package com.example.sociopinia;

import android.util.EventLog;
import android.util.Log;

import com.example.sociopinia.remote.api.SentimentAnalysisApi;
import com.example.sociopinia.remote.response.SentimentAnalysisResponse;
import com.example.sociopinia.util.EventCallback;

import java.io.IOException;
import java.util.EventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiRepository {

    private static ApiRepository instance;
    private SentimentAnalysisApi analysisApi;

    private ApiRepository(Retrofit retrofit) {
        analysisApi = retrofit.create(SentimentAnalysisApi.class);
    }

    public static ApiRepository getInstance() {
        return instance;
    }

    public static void init(Retrofit retrofit) {
        if(instance == null) {
            instance = new ApiRepository(retrofit);
        }
    }

    public void analyzeComments(String comment, EventCallback<SentimentAnalysisResponse> analyzeResult) {
        if(comment.isEmpty()) {
            analyzeResult.onResult(null);
            return;
        }
        analysisApi.analyzeContent(comment).enqueue(new Callback<SentimentAnalysisResponse>() {
            @Override
            public void onResponse(Call<SentimentAnalysisResponse> call, Response<SentimentAnalysisResponse> response) {
                Log.e("Response: ", response.body().toString());
                if(response.isSuccessful()) {
                    analyzeResult.onResult(response.body());
                } else {
                    try {
                        analyzeResult.onError(new Exception(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SentimentAnalysisResponse> call, Throwable t) {
                Log.e("Response: ", t.getLocalizedMessage());
                analyzeResult.onError(t);
            }
        });
    }


}
