package com.example.catapp.presentation.fragments.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.catapp.util.CatCategory
import com.example.catapp.util.getAllCatCategories
import com.example.catapp.R


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
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TopAppBar(
                title = {
                    Column {
                        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) { Text(stringResource(id = R.string.app_name)) }
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
            )
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
}

