package com.markettwits.siberspokedex.items.api

import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon

interface PokemonItemsFilter {

    /**
     * Gets the stat value for a Pokemon based on filter type.
     * @param pokemon The Pokemon to check
     * @param filter The stat filter type
     * @return The numeric value of the requested stat
     */
    fun getStatValue(pokemon: Pokemon, filter: FilterType): Int

    /**
     * Finds the strongest Pokemon based on active filters.
     * @param pokemons The list of Pokemons to analyze
     * @param filters The set of active filters
     * @return The strongest Pokemon or null if none found
     */
    fun getStrongestPokemon(
        pokemons: List<Pokemon>,
        filters: Set<FilterType>
    ): Pokemon?
}