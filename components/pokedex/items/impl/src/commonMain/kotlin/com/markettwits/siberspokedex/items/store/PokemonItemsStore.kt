package com.markettwits.siberspokedex.items.store

import app.cash.paging.PagingData
import com.arkivanov.mvikotlin.core.store.Store
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import com.markettwits.siberspokedex.items.api.FilterType
import com.markettwits.siberspokedex.items.store.PokemonItemsStore.*
import kotlinx.coroutines.flow.Flow

interface PokemonItemsStore : Store<Intent, State, Label> {


    data class State(
        val items : Flow<PagingData<Pokemon>>,
        val selectedFilters : Set<FilterType>,
        val selectedPokemonId : Int
    )


    sealed interface Intent {
        data class OnClickPokemon(val pokemon: Pokemon) : Intent

        data class OnClickFilter(val filter : FilterType) : Intent

        data object OnClickAllFilter : Intent

        data object OnClickRefresh : Intent
    }

    sealed interface Message {
        data class UpdatePokemonList(val items : Flow<PagingData<Pokemon>>) : Message

        data class UpdateFilter(val filter : Set<FilterType>) : Message

        data class UpdateHighlightPokemon(val pokemonId: Int) : Message

        data object UpdateHiHighlightPokemonCleared : Message
    }

    sealed interface Label {
        data class OnClickPokemon(val pokemon: Pokemon) : Label
    }

}
