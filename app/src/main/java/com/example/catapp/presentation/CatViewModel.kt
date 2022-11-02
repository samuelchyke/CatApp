package com.example.catapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.model.CatItem
import com.example.catapp.model.CatResponse
import com.example.catapp.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    val cat: MutableState<List<CatItem>> = mutableStateOf(listOf())

    val limit = mutableStateOf(10)

    val loading = mutableStateOf(true)

    init {
        searchCats()
    }

    fun searchCats() = viewModelScope.launch {
        loading.value = true
        val response = networkRepository.getListOfCats(limit.value)
        response.body()?.let {
            cat.value = it
        }
        loading.value = false
    }

}

