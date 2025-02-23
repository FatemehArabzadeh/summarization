package com.example.summarizer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("models/gemini-1.5-flash:generateContent")
    Call<SummarizeResponse> summarizeText(
            @Query("key") String apiKey,
            @Body SummarizeRequest request
    );
}


