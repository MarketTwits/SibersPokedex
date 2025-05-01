package com.markettwits.siberspokedex.items.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateLoading
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.itemKey
import com.markettwits.core_ui.items.screens.PullToRefreshScreen
import com.markettwits.core_ui.items.window.rememberScreenSizeInfo
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import kotlin.math.min

@Composable
fun PokemonItemsContent(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    pokemons: LazyPagingItems<Pokemon>,
    highlightedPokemonId: Int,
    onClickItem: (Pokemon) -> Unit,
) {
    val state = rememberLazyGridState()

    val highlightedPokemon = remember(pokemons, highlightedPokemonId) {
        pokemons.itemSnapshotList.items.firstOrNull { it.id == highlightedPokemonId }
    }

    LaunchedEffect(highlightedPokemonId) {
        if (highlightedPokemonId != 0) {
            state.animateScrollToItem(0)
        }
    }

    PullToRefreshScreen(
        isRefreshing = isRefreshing,
        onRefresh = pokemons::refresh
    ) {
        val screenWidth = rememberScreenSizeInfo().wDP
        val maxColumns = 5
        val columnWidth = 200.dp
        val columns = min(maxColumns, (screenWidth / columnWidth).toInt())
        LazyVerticalGrid(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.outlineVariant),
            columns = GridCells.Fixed(columns),
            state = state,
            contentPadding = PaddingValues(8.dp),
        ) {
            highlightedPokemon?.let {
                stickyHeader(
                    key = "highlighted-${it.id}",
                    contentType = it
                ) { index ->
                    PokemonItemCard(
                        pokemon = it,
                        isHighlighted = true,
                        onClick = onClickItem
                    )
                }
            }

            items(
                pokemons.itemCount,
            ) { index ->
                pokemons[index]?.takeIf { it.id != highlightedPokemonId }?.let { pokemon ->
                    PokemonItemCard(
                        pokemon = pokemon,
                        isHighlighted = false,
                        onClick = onClickItem
                    )
                }
            }

            if (pokemons.loadState.append is LoadStateLoading) {
                items(20) {
                    PokemonItemShimmer()
                }
            }
        }
    }
}