package com.markettwits.siberspokedex.cloud.mapper

import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import com.markettwits.siberspokedex.cloud.cloud.model.PokemonStat
import com.markettwits.siberspokedex.cloud.cloud.model.PokemonType
import com.markettwits.siberspokedex.cloud.cloud.model.PokemonStatInfo
import com.markettwits.siberspokedex.cloud.cloud.model.PokemonTypeInfo
import com.markettwits.siberspokedex.cloud.model.PXPokemonResponse
import com.markettwits.siberspokedex.cloud.model.PXStatResponse
import com.markettwits.siberspokedex.cloud.model.PXTypeResponse

/**
 * Maps network response objects to domain models for the Pokedex application.
 * This class provides conversion methods between API response formats and application domain models.
 */
class PokedexNetworkMapper {

    fun mapPokemonResponse(
        pokemonResponse: List<PXPokemonResponse>
    ): List<Pokemon> =
        pokemonResponse.map {
            Pokemon(
                id = it.id,
                name = it.name,
                height = it.height,
                weight = it.weight,
                types = mapPokemonTypes(it.types),
                stats = mapPokemonStats(it.stats),
                image = it.spirites.frontDefault ?: it.spirites.frontShiny ?: ""
            )
        }

    fun mapPokemonStats(statsResponse: List<PXStatResponse>): List<PokemonStat> =
        statsResponse.map { stat ->
            PokemonStat(
                baseStat = stat.baseStat,
                effort = stat.effort,
                stat = PokemonStatInfo(
                    name = stat.statNamed.name,
                    url = stat.statNamed.url
                )
            )
        }

    fun mapPokemonTypes(typesResponse: List<PXTypeResponse>): List<PokemonType> =
        typesResponse.map { type ->
            PokemonType(
                slot = type.slot,
                type = PokemonTypeInfo(
                    name = type.type.name,
                    url = type.type.url
                )
            )
        }
}