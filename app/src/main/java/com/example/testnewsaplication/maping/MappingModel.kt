package com.example.testnewsaplication.maping

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class MappingModel (
@PrimaryKey
 var id:Int,

 @ColumnInfo
 val description: String?,
 @ColumnInfo
 val publishedAt: String,
 @ColumnInfo
 val title: String,
 @ColumnInfo
 val url: String,
 @ColumnInfo
 val urlToImage: String?,

 )

