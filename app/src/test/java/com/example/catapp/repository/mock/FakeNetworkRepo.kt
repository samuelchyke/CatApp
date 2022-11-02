package com.example.catapp.repository.mock

import com.example.catapp.model.CatResponse
import com.example.catapp.repository.NetworkRepository
import retrofit2.Response

class FakeNetworkRepo: NetworkRepository {

    private var networkError = false

    fun returnNetworkError(value: Boolean){
        networkError = value
    }

    override suspend fun getListOfCats(limit: Int, category: Int): Response<CatResponse> {
        return Response.success(CatResponse())
    }

}