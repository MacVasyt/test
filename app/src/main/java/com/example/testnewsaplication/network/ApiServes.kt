package com.example.testnewsaplication.network

import com.example.testnewsaplication.model_response.GetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServes {
    @GET("/v2/everything?q=tesla&from=2022-11-20&sortBy=publishedAt&apiKey=b4caa9458b0f4fba8e6ae9f8bacc0d8f")
    suspend fun getPageById(
        @Query("page") idResponse: Int
    ): Response<GetResponse>
}