package com.markettwits.siberspokedex.root

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.webhistory.WebNavigationOwner
import com.arkivanov.decompose.value.Value
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import com.markettwits.siberspokedex.item.api.PokemonItemComponent
import com.markettwits.siberspokedex.items.api.PokemonItemsComponent
import kotlinx.serialization.Serializable

@OptIn(ExperimentalDecomposeApi::class)
interface SibersPokedexRootComponent : WebNavigationOwner {

    val childStack: Value<ChildStack<*, Child>>

    @Serializable
    sealed class Config {
        @Serializable
        data object Items : Config()

        @Serializable
        data class Item(val pokemon : Pokemon) : Config()
    }

    sealed class Child {
        data class Items(val component: PokemonItemsComponent) : Child()

        data class Item(val component: PokemonItemComponent) : Child()

    }

}