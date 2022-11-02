package com.example.catapp.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.catapp.presentation.CatViewModel
import com.example.catapp.repository.mock.FakeNetworkRepo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class CatViewModelTest{

    private lateinit var viewModel: CatViewModel
    private lateinit var fakeNetworkRepo: FakeNetworkRepo

    @Before
    fun setUp() {

        fakeNetworkRepo = FakeNetworkRepo()
        viewModel = CatViewModel(FakeNetworkRepo())

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get cats from network`() {
        viewModel.searchCats()
        val cat = viewModel.cat.value
        assertThat(cat).isNotNull()
    }

}