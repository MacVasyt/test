package com.example.testnewsaplication.maping

import com.example.testnewsaplication.network.Repository
import com.example.testnewsaplication.util.ROOMREPOSITORY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Mapping(private val coroutineScope: CoroutineScope) {
    private val repository=Repository()
    private var iD:Int=0
    private var mapModelList:List<MappingModel>?= emptyList()

    suspend fun getList(ids: Int){
        val response =repository.getPageById(ids)

                mapModelList = response?.articles?.map{
                    MappingModel(
                    id = ++iD,
                    description = it.description,
                    publishedAt = it.publishedAt,
                    title = it.title,
                    url = it.url,
                    urlToImage = it.urlToImage
                )
            }
            mapModelList?.forEach(){
                insert(it){}
            }
        }

    private fun insert(newsModel: MappingModel, onSuccess:()->Unit)=
        coroutineScope.launch(Dispatchers.IO) {
            ROOMREPOSITORY.insertNews(newsModel){
                onSuccess()
            }
        }
}