package com.markettwits.siberspokedex.cloud.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PXTypeResponse(
    @SerialName("type")
    val type: PXResultResponse,
    @SerialName("slot")
    val slot : Int
)

