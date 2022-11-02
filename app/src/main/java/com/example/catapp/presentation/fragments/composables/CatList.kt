package com.example.catapp.presentation.fragments.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.catapp.model.CatItem

@Composable
    fun CatList(
    loading: Boolean,
    cats: List<CatItem>
    ) {
        Box(
            modifier = Modifier.background(color = MaterialTheme.colors.surface)
        ) {
            LazyColumn {
                itemsIndexed(
                    items = cats
                ) { index, cat ->
                    CatCard(
                        cat = cat,
                        onClick = {}
                    )
                }
            }
        }
        CircularIndeterminateProgressBar(isDisplayed = loading)
    }