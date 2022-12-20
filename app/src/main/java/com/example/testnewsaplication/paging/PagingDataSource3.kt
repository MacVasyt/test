package com.example.testnewsaplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testnewsaplication.maping.Mapping
import com.example.testnewsaplication.model_response.NewsContentResponse
import com.example.testnewsaplication.network.Repository

class PagingDataSource3(
private val repository: Repository,
private val mapping:Mapping
):PagingSource<Int,NewsContentResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsContentResponse> {
      return  try {

            val pageNumber = params.key ?: 1
            val previousKey = if (pageNumber==1) null else pageNumber-1
            val datResponse=repository.getPageById(pageNumber)
            mapping.getList(pageNumber)

            return LoadResult.Page(
                data = datResponse!!.articles,
                prevKey = previousKey,
                nextKey = pageNumber+1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsContentResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }
}

