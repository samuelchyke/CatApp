package com.example.catapp.presentation.fragments.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.catapp.util.CatCategory
import com.example.catapp.util.getAllCatCategories

@Composable
fun AppBar(
    selectedCategory: CatCategory?,
    selectedChip: Int,
    onExecuteSearch: () -> Unit,
    onSelectedCategoryChanged: (Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.primary,
        elevation = 8.dp,
    ) {
        ScrollableTabRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp, bottom = 2.dp),
            selectedTabIndex = selectedChip,
            edgePadding = 2.dp,
            indicator = {
                TabRowDefaults.Indicator(
                    color = Color.White,
                    height = 0.dp,
                    modifier = Modifier.tabIndicatorOffset(it[0])
                )
            }
        ) {
            for (category in getAllCatCategories()) {
                CatCategoryChip(
                    category = category.value,
                    categoryTitle = category.named,
                    isSelected = selectedCategory == category,
                    onExecuteSearch = { onExecuteSearch() },
                    onSearchCategoryChanged = {
                        onSelectedCategoryChanged(it)
                    }
                )
            }
        }
    }
}
