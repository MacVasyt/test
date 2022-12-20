package com.example.testnewsaplication.network

import com.example.testnewsaplication.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetWork {
    private val retrofit by lazy {
         Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()}

    private val apiServes:ApiServes by lazy { retrofit.create(ApiServes::class.java)}

    val apiClient=ApiClient(apiServes)
}