package com.example.catapp.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.catapp.model.CatResponse
import com.example.catapp.network.CatServiceApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class NetworkRepositoryImplTest {

    private lateinit var repository : NetworkRepositoryImpl

    @Mock
    lateinit var api: CatServiceApi

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = NetworkRepositoryImpl(api)
    }

    @Test
    fun `get cat response`() {
        runBlocking {
            Mockito.`when`(api.getListOfCats()).thenReturn(Response.success(CatResponse()))
            val response = repository.getListOfCats(1,2)
            assertThat(CatResponse() == (response.body()))
        }
    }
}