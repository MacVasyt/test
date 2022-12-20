package com.example.testnewsaplication.db.repository

import com.example.testnewsaplication.maping.MappingModel

interface NewsRepository {
      suspend fun allNews():List<MappingModel>?
    suspend fun insertNews(newsModel: MappingModel, onSuccess: () -> Unit)
    suspend fun deleteNews(newsModel: MappingModel, onSuccess: () -> Unit)
}