package com.markettwits.siberspokedex.items.components

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import app.cash.paging.compose.collectAsLazyPagingItems
import com.markettwits.core_ui.items.screens.AdaptivePane
import com.markettwits.core_ui.items.screens.CollapsingToolbarRefreshScaffold
import com.markettwits.siberspokedex.items.api.PokemonItemsComponent

@Composable
fun PokemonItemsPane(
    modifier: Modifier = Modifier,
    component: PokemonItemsComponent
) {

    val state by component.state.collectAsState()

    val items = state.items.collectAsLazyPagingItems()

    CollapsingToolbarRefreshScaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.outlineVariant),
        isRefreshing = false,
        onRefresh = items::refresh,
        toolbar = {
            PokemonListTopBar(
                selectedFilters = state.selectedFilters,
                onFilterToggle = component::onClickFilter,
                onClickAllToggle = component::onClickAllFilter
            )
        },
        body = { innerModifier ->
            AdaptivePane {
                PokemonsContent(
                    modifier = innerModifier,
                    pokemons = items,
                    highlightedPokemonId = state.selectedPokemonId ?: 0,
                    onClick = {
                        component.onClickItem(it)
                    }
                )
            }
        }
    )
}