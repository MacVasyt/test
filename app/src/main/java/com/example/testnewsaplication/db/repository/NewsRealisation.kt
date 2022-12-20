package com.example.testnewsaplication.db.repository

import com.example.testnewsaplication.db.dao.NewsDao
import com.example.testnewsaplication.maping.MappingModel

class NewsRealisation(private var newsDao: NewsDao):NewsRepository {

    override suspend fun allNews(): List<MappingModel>? {
        return newsDao.getAllNews()
    }

    override suspend fun insertNews(newsModel: MappingModel, onSuccess: () -> Unit) {
        newsDao.insert(newsModel)
        onSuccess()
    }

    override suspend fun deleteNews(newsModel: MappingModel, onSuccess: () -> Unit) {
        newsDao.delete(newsModel)
        onSuccess()
    }
}