package com.markettwits.siberspokedex.items.store

import app.cash.paging.Pager
import app.cash.paging.cachedIn
import app.cash.paging.createPagingConfig
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.markettwits.core.log.LogTagProvider
import com.markettwits.core.log.infoLog
import com.markettwits.siberspokedex.cloud.cloud.api.PokedexApi
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import com.markettwits.siberspokedex.items.api.FilterType
import com.markettwits.siberspokedex.items.api.PokemonItemsFilter
import com.markettwits.siberspokedex.items.api.PokemonPagingSource
import com.markettwits.siberspokedex.items.api.SHOP_ITEMS_PAGE_SIZE
import com.markettwits.siberspokedex.items.store.PokemonItemsStore.*
import com.markettwits.siberspokedex.items.store.PokemonItemsStore.Label.*
import kotlinx.coroutines.launch

internal class PokemonItemsExecutor(
    private val pokemonApi: PokedexApi,
    private val pokemonItemsFilter : PokemonItemsFilter
) : CoroutineExecutor<Intent, Unit, State, Message, Label>(), LogTagProvider {

    override val tag: String = "PokemonItemsExecutor"
    private val currentFilters = mutableSetOf<FilterType>()

    override fun executeIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickPokemon -> publish(OnClickPokemon(intent.pokemon))
            is Intent.OnClickRefresh -> getPokemons()
            is Intent.OnClickFilter -> handleFilterToggle(intent.filter)
            is Intent.OnClickAllFilter -> handleAllFilter()
        }
    }

    override fun executeAction(action: Unit) {
        getPokemons()
    }

    private fun getPokemons() {
        scope.launch {
            infoLog { "launch to fetch pokemons" }
            val pager = createPagingSource(
                onStrongestPokemonFound = {
                    dispatch(Message.UpdateHighlightPokemon(it.id))
                },
                onStrongestPokemonCleared = {
                    dispatch(Message.UpdateHiHighlightPokemonCleared)
                }
            )
            dispatch(Message.UpdatePokemonList(pager.flow.cachedIn(scope)))
        }
    }

    private fun handleAllFilter(){
        infoLog { "handleAllFilter" }
        currentFilters.clear()
        dispatch(Message.UpdateFilter(currentFilters.toSet()))
        getPokemons()
    }

    private fun handleFilterToggle(filter: FilterType) {
        infoLog { "click filter" }
        if (currentFilters.contains(filter)) {
            currentFilters.remove(filter)
        } else {
            currentFilters.add(filter)
        }
        dispatch(Message.UpdateFilter(currentFilters.toSet()))
        getPokemons()
    }

    private fun createPagingSource(
        onStrongestPokemonFound: (Pokemon) -> Unit,
        onStrongestPokemonCleared: () -> Unit,
    ): Pager<Int, Pokemon> {

        return Pager<Int, Pokemon>(
            config = createPagingConfig(
                pageSize = SHOP_ITEMS_PAGE_SIZE,
                initialLoadSize = SHOP_ITEMS_PAGE_SIZE
            ),
            initialKey = null,
            pagingSourceFactory = {
                PokemonPagingSource(
                    pokedexApi = pokemonApi,
                    pokedexFilter = pokemonItemsFilter,
                    activeFilters = currentFilters.toSet(),
                    onStrongestPokemonFound = onStrongestPokemonFound,
                    onStrongestPokemonCleared = onStrongestPokemonCleared
                )
            })
    }
}