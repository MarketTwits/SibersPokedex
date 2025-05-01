package com.markettwits.siberspokedex.items.api

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.markettwits.siberspokedex.cloud.api.pokedexApiModule
import com.markettwits.siberspokedex.cloud.cloud.api.PokedexApi
import com.markettwits.siberspokedex.items.store.PokemonItemsStoreFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val pokemonItemsModule = module {
    includes(pokedexApiModule)
    singleOf(::DefaultPokemonItemsDependencies) bind PokemonItemsDependencies::class
    singleOf(::DefaultStoreFactory) bind StoreFactory::class
    singleOf(::PokemonItemsFilterImpl) bind PokemonItemsFilter::class
    singleOf(::PokemonItemsStoreFactory)
}

private class DefaultPokemonItemsDependencies(
    override val pokemonApi: PokedexApi,
    override val storeFactory: StoreFactory,
    override val pokemonItemsFilter: PokemonItemsFilter
) : PokemonItemsDependencies