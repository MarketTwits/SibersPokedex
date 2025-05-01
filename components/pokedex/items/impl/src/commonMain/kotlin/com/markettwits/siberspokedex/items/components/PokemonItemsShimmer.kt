package com.markettwits.siberspokedex.items.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonItemsShimmer(modifier: Modifier = Modifier){
    val backgroundColor = MaterialTheme.colorScheme.background
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        columns = GridCells.Adaptive(400.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(30) {
            PokemonItemShimmer()
        }
    }
}