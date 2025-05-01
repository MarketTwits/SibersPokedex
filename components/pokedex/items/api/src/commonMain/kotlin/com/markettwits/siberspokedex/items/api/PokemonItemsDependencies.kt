package com.markettwits.siberspokedex.items.api

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.markettwits.siberspokedex.cloud.cloud.api.PokedexApi

interface PokemonItemsDependencies {
    val pokemonApi: PokedexApi
    val pokemonItemsFilter : PokemonItemsFilter
    val storeFactory: StoreFactory
}