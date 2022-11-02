package com.example.catapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.model.CatItem
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

    val page = mutableStateOf(1)

    val category = mutableStateOf(2)

    val selectedCategory: MutableState<CatCategory?> = mutableStateOf(CatCategory.SPACE)

    val scrollTabPosition = mutableStateOf(0)

    val loading = mutableStateOf(true)

    private var catListScrollPosition = 0


    init {
        searchCats()
    }

    fun searchCats() = viewModelScope.launch {
        loading.value = true
        clearCatList()
        val response = networkRepository.getListOfCats(page.value, category.value)
        response.body()?.let {
            cat.value = it
        }
        loading.value = false
    }

    fun nextPage(){
        viewModelScope.launch {
            // prevent duplicate event due to recompose happening to quickly
            if((catListScrollPosition + 1) >= (page.value * 10)){
                loading.value = true
                incrementPage()

                if(page.value > 1){
                    val response = networkRepository.getListOfCats(page.value, category.value)
                    response.body()?.let {
                        appendCats(it)
                    }
                }
                loading.value = false
            }
        }
    }

    private fun appendCats(cats: List<CatItem>){
        val currentList = ArrayList(this.cat.value)
        currentList.addAll(cats)
        this.cat.value = currentList
    }

    private fun incrementPage(){
        page.value ++
    }

    fun onSelectedCategoryChanged(category: Int) {
        this.page.value = 1
        val newCategory = getCatCategory(category)
        this.selectedCategory.value = newCategory
        this.scrollTabPosition.value = newCategory?.ordinal ?: 0
        onCategoryChanged(category)
    }

    private fun onCategoryChanged(category: Int) {
        this.category.value = category
    }

    fun onChangedCatResultPosition(position: Int){
        catListScrollPosition = position
    }

    private fun clearCatList(){
        cat.value = listOf()
    }
}

