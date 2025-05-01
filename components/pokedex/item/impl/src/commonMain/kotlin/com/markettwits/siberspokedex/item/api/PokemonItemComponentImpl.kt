package com.markettwits.siberspokedex.item.api

import com.arkivanov.decompose.ComponentContext
import com.markettwits.core.decompose.DecomposeOnBackParameter
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PokemonItemComponentImpl(
    componentContext: ComponentContext,
    pokemon: Pokemon,
    private val onBack: DecomposeOnBackParameter,
) : PokemonItemComponent, ComponentContext by componentContext {

    override val state: StateFlow<Pokemon> = MutableStateFlow(pokemon)

    override fun onClickGoBack() = onBack()

}
