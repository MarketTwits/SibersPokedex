package com.markettwits.siberspokedex.items.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.webhistory.WebNavigation
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.markettwits.core.decompose.componentScope
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import com.markettwits.siberspokedex.items.store.PokemonItemsStore
import com.markettwits.siberspokedex.items.store.PokemonItemsStoreFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class PokemonItemsComponentImpl(
    componentContext: ComponentContext,
    private val dependencies: PokemonItemsDependencies,
    private val onClickPokemon: (Pokemon) -> Unit,
) : PokemonItemsComponent, ComponentContext by componentContext {
    private val store: PokemonItemsStore = PokemonItemsStoreFactory(
        storeFactory = dependencies.storeFactory,
        pokedexApi = dependencies.pokemonApi,
        pokemonItemsFilter = dependencies.pokemonItemsFilter
    ).create()

    override val state: StateFlow<PokemonItemsState> = store.stateFlow.toUiState()

    override fun onClickItem(pokemon: Pokemon) {
        store.accept(PokemonItemsStore.Intent.OnClickPokemon(pokemon))
    }

    override fun onClickFilter(filter: FilterType) {
        store.accept(PokemonItemsStore.Intent.OnClickFilter(filter))
    }

    override fun onClickAllFilter() {
        store.accept(PokemonItemsStore.Intent.OnClickAllFilter)
    }

    init {
        store.labels.onEach { label ->
            when (label) {
                is PokemonItemsStore.Label.OnClickPokemon -> onClickPokemon(label.pokemon)
            }
        }.launchIn(componentScope)
    }

    private fun StateFlow<PokemonItemsStore.State>.toUiState(): StateFlow<PokemonItemsState> {
        return map { storeState ->
            PokemonItemsState(
                items = storeState.items,
                selectedFilters = storeState.selectedFilters,
                selectedPokemonId = storeState.selectedPokemonId
            )
        }.stateIn(
            scope = componentScope,
            started = SharingStarted.Eagerly,
            initialValue = PokemonItemsState.INITIAL
        )
    }

}
