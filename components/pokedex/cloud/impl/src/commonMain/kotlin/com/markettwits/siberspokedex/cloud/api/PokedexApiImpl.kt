package com.markettwits.siberspokedex.cloud.api

import com.markettwits.siberspokedex.cloud.cloud.api.PokedexApi
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import com.markettwits.siberspokedex.cloud.mapper.PokedexNetworkMapper

/**
 * Implementation of the PokedexApi interface.
 * Handles network operations and data mapping.
 *
 * @property pokedexNetworkApi The low-level network API client
 * @property mapper Converts network responses to domain models
 */
internal class PokedexApiImpl(
    private val pokedexNetworkApi: PokedexNetworkApi,
    private val mapper: PokedexNetworkMapper
) : PokedexApi {

    /**
     * Fetches Pokemon list from network and converts to domain models.
     *
     * @param limit Maximum number of items to return
     * @param offset Starting position in the collection
     * @return List of mapped Pokemon domain objects
     */
    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): List<Pokemon> {
        val response = pokedexNetworkApi.getPokemonDetailedList(limit, offset)
        return mapper.mapPokemonResponse(response)
    }
}