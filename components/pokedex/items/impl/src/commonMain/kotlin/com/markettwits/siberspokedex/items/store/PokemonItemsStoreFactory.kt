package com.markettwits.siberspokedex.items.store

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.markettwits.siberspokedex.cloud.cloud.api.PokedexApi
import com.markettwits.siberspokedex.items.api.PokemonItemsFilter
import com.markettwits.siberspokedex.items.store.PokemonItemsStore.*
import kotlinx.coroutines.flow.emptyFlow

internal class PokemonItemsStoreFactory(
    private val storeFactory: StoreFactory,
    private val pokedexApi: PokedexApi,
    private val pokemonItemsFilter: PokemonItemsFilter
) {

    fun create(): PokemonItemsStore = PokemonItemsStoreImpl()

    private inner class PokemonItemsStoreImpl() : PokemonItemsStore,
        Store<Intent, State, Label> by storeFactory.create(
            name = "SettingsStore",
            initialState = State(emptyFlow(), emptySet(), 0),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = { PokemonItemsExecutor(pokedexApi, pokemonItemsFilter) },
            reducer = PokemonItemsReducer
        )
}