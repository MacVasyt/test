package com.example.testnewsaplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testnewsaplication.db.dao.NewsDao
import com.example.testnewsaplication.maping.MappingModel

@Database(entities = [MappingModel::class], version = 1)
abstract class NewsDataBase: RoomDatabase() {
    abstract fun getNewsDao():NewsDao

    companion object {
        private var database:NewsDataBase?=null

        @Synchronized
        fun getInstance(context: Context):NewsDataBase{
            return if (database==null){
                database= Room.databaseBuilder(context,NewsDataBase::class.java,"db").build()
                database as NewsDataBase
            }else
                database as NewsDataBase
        }
    }
}