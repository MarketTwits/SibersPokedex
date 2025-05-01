package com.markettwits.siberspokedex.cloud.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PXPokemonResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("height")
    val height: Int,
    @SerialName("weight")
    val weight: Int,
    @SerialName("sprites")
    val spirites: PXSpiritesResponse,
    @SerialName("stats")
    val stats: List<PXStatResponse>,
    @SerialName("types")
    val types: List<PXTypeResponse>
)
