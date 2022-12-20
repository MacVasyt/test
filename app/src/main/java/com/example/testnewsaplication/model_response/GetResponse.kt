package com.example.testnewsaplication.model_response

data class GetResponse(
    val articles: List<NewsContentResponse>,
    val status: String,
    val totalResults: Int
)

