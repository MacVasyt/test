package com.example.testnewsaplication.model_response


data class NewsContentResponse(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)  {
    data class Source(
        val id: String,
        val name: String
    )
}
