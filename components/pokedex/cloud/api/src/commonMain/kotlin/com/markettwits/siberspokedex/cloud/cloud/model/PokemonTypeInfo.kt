package com.markettwits.siberspokedex.cloud.cloud.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeInfo(
    val name: String,
    val url: String
)