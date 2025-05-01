package com.markettwits.siberspokedex.items.api

import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import kotlinx.coroutines.flow.StateFlow

interface PokemonItemsComponent {

    val state : StateFlow<PokemonItemsState>

    fun onClickItem(pokemon: Pokemon)

    fun onClickFilter(filter : FilterType)

    fun onClickAllFilter()

}