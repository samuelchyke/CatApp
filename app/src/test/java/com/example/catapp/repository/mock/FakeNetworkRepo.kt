package com.example.catapp.repository.mock

import com.example.catapp.model.CatItem
import com.example.catapp.model.CatResponse
import com.example.catapp.repository.NetworkRepository
import retrofit2.Response

class FakeNetworkRepo: NetworkRepository {

    override suspend fun getListOfCats(page: Int, category: Int): Response<CatResponse> {
        return Response.success(CatResponse())
    }

}