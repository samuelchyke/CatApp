package com.example.catapp.repository

import com.example.catapp.model.CatResponse
import com.example.catapp.network.CatServiceApi
import retrofit2.Response
import javax.inject.Inject

interface NetworkRepository {

    suspend fun getListOfCats(limit: Int) : Response<CatResponse>

}

class NetworkRepositoryImpl @Inject constructor(
    private val catServiceApi: CatServiceApi
):NetworkRepository{

    override suspend fun getListOfCats(limit: Int): Response<CatResponse> {
        return catServiceApi.getListOfCats(limit = limit)
    }

}