package com.markettwits.siberspokedex.items.api

import com.markettwits.core.paging.OffsetAndLimitPagingSourceNew
import com.markettwits.siberspokedex.cloud.cloud.api.PokedexApi
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon

internal const val SHOP_ITEMS_PAGE_SIZE = 30

/**
 * Paging source implementation for loading Pokemon data with caching support.
 * Handles both network and cached data loading with strongest Pokemon detection.
 * @property pokedexApi The API service for Pokemon data
 * @property activeFilters Set of active filters for strongest Pokemon detection
 * @property onStrongestPokemonFound Callback when strongest Pokemon is found
 * @property onStrongestPokemonCleared Callback when strongest Pokemon selection is cleared
 * @property pokemonCache The cache instance for Pokemon data
 */
internal class PokemonPagingSource(
    private val pokedexApi: PokedexApi,
    private val pokedexFilter : PokemonItemsFilter,
    private val activeFilters: Set<FilterType>,
    private val onStrongestPokemonFound: (Pokemon) -> Unit,
    private val onStrongestPokemonCleared: () -> Unit,
    private val pokemonCache: PokemonCache = PokemonCache()
) : OffsetAndLimitPagingSourceNew<Pokemon>(SHOP_ITEMS_PAGE_SIZE) {

    private val cachedItems = mutableListOf<Pokemon>()

    override suspend fun load(offset: Int, limit: Int): List<Pokemon> {
        val cachedData = loadFromCache(offset, limit)
        return cachedData.ifEmpty {
            loadFromNetwork(offset, limit)
        }
    }
    /**
     * Loads Pokemon data from cache.
     * @param offset The starting position
     * @param limit The maximum number of items to load
     * @return List of Pokemons from cache
     */
    private suspend fun loadFromCache(offset: Int, limit: Int): List<Pokemon> {
        val allCached = pokemonCache.getList()
        return allCached
            .drop(offset)
            .take(limit)
            .toList()
            .also { page ->
                cachedItems += page
                processStrongestPokemon(cachedItems)
            }
    }

    /**
     * Loads Pokemon data from network and updates cache.
     * @param offset The starting position
     * @param limit The maximum number of items to load
     * @return List of Pokemons from network
     */
    private suspend fun loadFromNetwork(offset: Int, limit: Int): List<Pokemon> {
        return try {
            pokedexApi.getPokemonList(offset, limit).also { pokemons ->
                pokemons.forEach { pokemonCache.set(it.id, it) }
                cachedItems += pokemons
                processStrongestPokemon(cachedItems)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Processes the current Pokemon list to find the strongest one based on active filters.
     * @param pokemons The list of Pokemons to analyze
     */
    private fun processStrongestPokemon(pokemons: List<Pokemon>) {
        if (activeFilters.isNotEmpty()) {
            val strongest = pokedexFilter.getStrongestPokemon(pokemons, activeFilters)
            strongest?.let { onStrongestPokemonFound(it) }
        } else {
            onStrongestPokemonCleared()
        }
    }

}
