package com.example.catapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.model.CatItem
import com.example.catapp.model.CatResponse
import com.example.catapp.repository.NetworkRepository
import com.example.catapp.util.CatCategory
import com.example.catapp.util.getCatCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    val cat: MutableState<List<CatItem>> = mutableStateOf(listOf())

    val limit = mutableStateOf(10)

    val category = mutableStateOf(2)

    val selectedCategory: MutableState<CatCategory?> = mutableStateOf(CatCategory.SPACE)

    val scrollTabPosition = mutableStateOf(0)

    val loading = mutableStateOf(true)

    init {
        searchCats()
    }

    fun searchCats() = viewModelScope.launch {
        loading.value = true
        val response = networkRepository.getListOfCats(limit.value, category.value)
        response.body()?.let {
            cat.value = it
        }
        loading.value = false
    }

    fun onSelectedCategoryChanged(category: Int) {
        this.limit.value = 10
        val newCategory = getCatCategory(category)
        this.selectedCategory.value = newCategory
        this.scrollTabPosition.value = newCategory?.ordinal ?: 0
        onCategoryChanged(category)
    }

    private fun onCategoryChanged(category: Int) {
        this.category.value = category
    }

}

