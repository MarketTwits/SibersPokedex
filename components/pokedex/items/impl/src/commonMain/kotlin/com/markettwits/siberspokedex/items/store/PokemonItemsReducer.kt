package com.markettwits.siberspokedex.items.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.markettwits.siberspokedex.items.store.PokemonItemsStore.State
import com.markettwits.siberspokedex.items.store.PokemonItemsStore.Message

internal object PokemonItemsReducer : Reducer<State, Message> {
    override fun State.reduce(msg: Message): State {
        return when (msg) {
            is Message.UpdatePokemonList -> copy(items = msg.items)
            is Message.UpdateFilter -> copy(selectedFilters = msg.filter)
            is Message.UpdateHighlightPokemon -> copy(selectedPokemonId = msg.pokemonId)
            is Message.UpdateHiHighlightPokemonCleared -> copy(selectedPokemonId = 0)
        }
    }
}