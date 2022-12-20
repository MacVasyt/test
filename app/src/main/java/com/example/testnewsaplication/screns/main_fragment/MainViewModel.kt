package com.example.testnewsaplication.screns.main_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.testnewsaplication.db.NewsDataBase
import com.example.testnewsaplication.db.repository.NewsRealisation
import com.example.testnewsaplication.maping.Mapping
import com.example.testnewsaplication.maping.MappingModel
import com.example.testnewsaplication.network.Repository
import com.example.testnewsaplication.paging.PagingDataSource3
import com.example.testnewsaplication.util.ROOMREPOSITORY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository=Repository()
    private val context=application
    private val mapping=Mapping(viewModelScope)

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    init {
        viewModelScope.launch {
            delay(3000)
            _isLoading.value = false
        }
    }

    fun initDataBase(){
        val newsDao= NewsDataBase.getInstance(context).getNewsDao()
        ROOMREPOSITORY= NewsRealisation(newsDao)
    }

    suspend fun delete(newsModel: MappingModel, onSuccess:()->Unit)=
        ROOMREPOSITORY.deleteNews(newsModel){
                onSuccess()}

    suspend fun  getAllNews(): List<MappingModel>? {
        return ROOMREPOSITORY.allNews()
    }

    val flow = Pager(
        PagingConfig(
        pageSize = 100,
        prefetchDistance = 5,
        )
    ) {
        PagingDataSource3(repository,mapping)
    }.flow
        .cachedIn(viewModelScope)
}


