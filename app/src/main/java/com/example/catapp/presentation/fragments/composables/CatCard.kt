package com.example.catapp.presentation.fragments.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.catapp.model.CatItem
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
    fun CatCard(
        cat: CatItem,
        onClick: () -> Unit
    ) {
        Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(
                    bottom = 6.dp,
                    top = 6.dp
                )
                .fillMaxWidth()
                .clickable(onClick = onClick),
            elevation = 8.dp
        ) {
            Column {
                GlideImage(
                    imageModel = cat.url,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(225.dp),
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    ),
                    //Loading Image with shimmer
                    component = rememberImageComponent {
                        // shows a shimmering effect when loading an image.
                        +ShimmerPlugin(
                            baseColor = Color.Gray,
                            highlightColor = Color.White
                        )
                    }
                )
            }
        }
    }