package com.markettwits.siberspokedex.item.api

import com.arkivanov.decompose.ComponentContext
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import kotlinx.coroutines.flow.StateFlow

interface PokemonItemComponent  {

    val state : StateFlow<Pokemon>

    fun onClickGoBack()

}