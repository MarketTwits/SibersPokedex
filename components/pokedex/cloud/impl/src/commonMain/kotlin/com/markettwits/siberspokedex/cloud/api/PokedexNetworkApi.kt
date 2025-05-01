package com.markettwits.siberspokedex.cloud.api

import com.markettwits.siberspokedex.cloud.model.PXPokemonListResponse
import com.markettwits.siberspokedex.cloud.model.PXPokemonResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.collections.map

internal class PokedexNetworkApi(
    private val httpClient: HttpClient,
)  {

    suspend fun getPokemonList(limit: Int, offset: Int): PXPokemonListResponse {
        return httpClient.get("pokemon?limit=$limit&offset=$offset").body<PXPokemonListResponse>()
    }

    suspend fun getPokemonDetail(name: String): PXPokemonResponse {
        return httpClient.get("pokemon/$name").body<PXPokemonResponse>()
    }

    suspend fun getPokemonDetailedList(limit: Int, offset: Int): List<PXPokemonResponse> {
        val pokemonList = getPokemonList(limit, offset)
        return coroutineScope {
            pokemonList.results.map { pokemon ->
                async { getPokemonDetail(pokemon.name) }
            }.map { it.await() }
        }
    }
}