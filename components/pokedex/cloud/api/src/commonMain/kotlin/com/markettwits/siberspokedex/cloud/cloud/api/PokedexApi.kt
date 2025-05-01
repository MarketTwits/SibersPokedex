package com.markettwits.siberspokedex.cloud.cloud.api

import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon

/**
 * Interface defining the Pokedex API operations.
 * Provides a clean abstraction for Pokemon data retrieval.
 */
interface PokedexApi {

    /**
     * Retrieves a list of Pokemon with detailed information.
     *
     * @param limit Maximum number of items to return
     * @param offset Starting position in the collection
     * @return List of Pokemon domain objects
     */
    suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon>

}