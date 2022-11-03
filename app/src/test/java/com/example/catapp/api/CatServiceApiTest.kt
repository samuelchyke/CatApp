package com.example.catapp.api

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.catapp.network.CatServiceApi
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class CatServiceApiTest {

    lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: CatServiceApi
    lateinit var gson: Gson
    lateinit var search : String

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(CatServiceApi::class.java)
        search = "/search?page=1&mime_types=gif&limit=10&category_ids=2"
    }

    @Test
    fun`get list of cats api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("[]"))
            val response = apiService.getListOfCats()
            val request = mockWebServer.takeRequest()
            assertEquals(search,request.path)
            assertThat(true).isEqualTo(response.body()?.isEmpty())
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}