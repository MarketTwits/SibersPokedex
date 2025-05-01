package com.markettwits.siberspokedex.cloud.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PXPokemonListResponse(
    @SerialName("results")
    val results: List<PXResultResponse>,
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val nextPage: String?,
    @SerialName("previous")
    val previousPage: String?,
)
