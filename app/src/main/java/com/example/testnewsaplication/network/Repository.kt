package com.example.testnewsaplication.network

import com.example.testnewsaplication.model_response.GetResponse

class Repository(){

    suspend fun getPageById(id:Int):GetResponse? {
        val request=NetWork.apiClient.getPageById(id)
        if (request.failed || !request.isSuccessful)
            return null

            return request.body
    }
}