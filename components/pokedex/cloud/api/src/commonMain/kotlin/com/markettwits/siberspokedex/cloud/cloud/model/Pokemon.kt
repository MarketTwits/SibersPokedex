package com.markettwits.siberspokedex.cloud.cloud.model

import kotlinx.serialization.Serializable


@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonType>,
    val stats: List<PokemonStat>,
    val image: String
)

fun Pokemon.getStatByName(name: String): Int = stats.find { it.stat.name == name }?.baseStat ?: 0