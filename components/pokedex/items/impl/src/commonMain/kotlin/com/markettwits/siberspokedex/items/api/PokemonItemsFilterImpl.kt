package com.markettwits.siberspokedex.items.api

import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon

class PokemonItemsFilterImpl : PokemonItemsFilter {

    /**
     * Gets the stat value for a Pokemon based on filter type.
     * @param pokemon The Pokemon to check
     * @param filter The stat filter type
     * @return The numeric value of the requested stat
     */
    override fun getStatValue(pokemon: Pokemon, filter: FilterType): Int {
        val statName = when (filter) {
            FilterType.ATTACK -> "attack"
            FilterType.DEFENSE -> "defense"
            FilterType.HP -> "hp"
        }

        return pokemon.stats.firstOrNull {
            it.stat.name.equals(statName, ignoreCase = true)
        }?.baseStat ?: 0
    }

    /**
     * Finds the strongest Pokemon based on active filters.
     * @param pokemons The list of Pokemons to analyze
     * @param filters The set of active filters
     * @return The strongest Pokemon or null if none found
     */
    override fun getStrongestPokemon(
        pokemons: List<Pokemon>,
        filters: Set<FilterType>
    ): Pokemon? {
        val topCandidatesByStat = filters.map { filter ->
            val maxValue = pokemons.maxOfOrNull { getStatValue(it, filter) } ?: return null
            filter to pokemons.filter { getStatValue(it, filter) == maxValue }
        }
        val intersection = topCandidatesByStat
            .map { it.second.toSet() }
            .reduceOrNull { acc, set -> acc.intersect(set) }

        return when {
            intersection.isNullOrEmpty() -> null
            intersection.size == 1 -> intersection.first()
            else -> intersection.first()
        }
    }

}