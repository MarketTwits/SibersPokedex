package com.markettwits.siberspokedex.cloud.cloud.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonType(
    val slot: Int,
    val type: PokemonTypeInfo
)