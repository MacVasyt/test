package com.example.testnewsaplication.db.dao

import androidx.room.*
import com.example.testnewsaplication.maping.MappingModel

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(newsModel: MappingModel)

    @Delete
    suspend fun delete(newsModel: MappingModel)

    @Query("SELECT*from news_table")
     suspend fun getAllNews(): List<MappingModel>?

}