package com.example.catapp.network

import com.example.catapp.model.CatResponse
import com.example.catapp.network.CatServiceApi.Companion.CAT_SEARCH_PATH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatServiceApi {

    @GET(CAT_SEARCH_PATH)
    suspend fun getListOfCats(
        @Header("x-api-key") authToken : String = AUTH_TOKEN,
        @Query("page") page : Int = 1,
        @Query("mime_types") memeType : String = "gif",
        @Query("limit") limit : Int = 10,
        @Query("category_ids") category_Ids : Int = 2,
    ) : Response<CatResponse>

    companion object{

        const val BASE_URL = "https://api.thecatapi.com/v1/images/"

        private const val CAT_SEARCH_PATH = "search"
        private const val AUTH_TOKEN = "live_hGjvw5dZ0YwvkHwuRmdn7whLTJ6LtYH6cjyFj6mkDcL1pxJPBIgWRQR9T2MPAdvp"

    }


}
