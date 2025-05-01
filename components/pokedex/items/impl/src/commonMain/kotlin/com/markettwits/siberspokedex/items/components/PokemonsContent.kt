package com.markettwits.siberspokedex.items.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.itemKey
import com.markettwits.core.paging.fold
import com.markettwits.core_ui.items.theme.FontNunito
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon

@Composable
fun PokemonsContent(
    modifier: Modifier = Modifier,
    pokemons: LazyPagingItems<Pokemon>,
    highlightedPokemonId: Int,
    onClick: (Pokemon) -> Unit
) {
    pokemons.fold(onLoading = {
        PokemonItemsShimmer(modifier = modifier)
    }, onException = {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Exception while loading pokemons ${it.message}",
                fontFamily = FontNunito.semiBoldBold(),
                fontSize = 16.sp,
            )
        }
    }, onSuccess = { isRefreshing ->
        PokemonItemsContent(
            isRefreshing = isRefreshing,
            highlightedPokemonId = highlightedPokemonId,
            pokemons = pokemons,
            onClickItem = onClick
        )
    }, onEmpty = {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Pokemons not found",
                fontFamily = FontNunito.semiBoldBold(),
                fontSize = 16.sp,
            )
        }
    }
    )
}