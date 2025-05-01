package com.markettwits.siberspokedex.cloud.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PXStatResponse(
    @SerialName("base_stat")
    val baseStat: Int,
    @SerialName("effort")
    val effort : Int,
    @SerialName("stat")
    val statNamed: PXResultResponse
)


