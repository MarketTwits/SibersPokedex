package com.markettwits.siberspokedex.cloud.cloud.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonStat(
    val baseStat: Int,
    val effort: Int,
    val stat: PokemonStatInfo
)