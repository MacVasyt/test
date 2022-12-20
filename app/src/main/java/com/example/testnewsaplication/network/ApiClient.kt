package com.example.testnewsaplication.network

import com.example.testnewsaplication.model_response.GetResponse
import retrofit2.Response

class ApiClient (private val apiServes: ApiServes){
    suspend fun getPageById(pageId:Int): SimpleResponse<GetResponse> {
        return safeApiCall{ apiServes.getPageById(pageId) }
    }
    private inline fun <T> safeApiCall(apiCall:()-> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())

        }catch (e:Exception){
            SimpleResponse.failure(e)
        }
    }
}