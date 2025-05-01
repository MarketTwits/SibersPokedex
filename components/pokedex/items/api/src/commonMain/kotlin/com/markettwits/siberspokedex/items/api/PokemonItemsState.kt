package com.markettwits.siberspokedex.items.api

import app.cash.paging.PagingData
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class PokemonItemsState(
    val items: Flow<PagingData<Pokemon>>,
    val selectedFilters: Set<FilterType>,
    val selectedPokemonId: Int? = null
) {
    companion object {
        val INITIAL: PokemonItemsState = PokemonItemsState(emptyFlow(), emptySet(), null)
    }
}

