package com.markettwits.siberspokedex.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.router.webhistory.WebNavigation
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.markettwits.ComponentKoinContext
import com.markettwits.core.decompose.DecomposeOnBackParameter
import com.markettwits.siberspokedex.item.api.PokemonItemComponentImpl
import com.markettwits.siberspokedex.items.api.PokemonItemsComponentImpl
import com.markettwits.siberspokedex.items.api.pokemonItemsModule
import com.markettwits.siberspokedex.root.SibersPokedexRootComponent.Child.Item
import com.markettwits.siberspokedex.root.SibersPokedexRootComponent.Child.Items

class SibersPokedexRootComponentImpl(
    componentContext: ComponentContext,
    private val deepLinkUrl: Url? = null
) : ComponentContext by componentContext, SibersPokedexRootComponent {

    private val scope = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }.getOrCreateKoinScope(listOf(pokemonItemsModule))

    private val stackNavigation = StackNavigation<SibersPokedexRootComponent.Config>()

    private val _stack =
        childStack(
            source = stackNavigation,
            serializer = SibersPokedexRootComponent.Config.serializer(),
            initialStack = { getInitialStack(deepLinkUrl) },
            childFactory = ::child,
        )

    override val childStack: Value<ChildStack<*, SibersPokedexRootComponent.Child>> = _stack

    @OptIn(ExperimentalDecomposeApi::class)
    override val webNavigation: WebNavigation<*> = childStackWebNavigation(
        navigator = stackNavigation,
        stack = _stack,
        serializer = SibersPokedexRootComponent.Config.serializer(),
        pathMapper = { it.configuration.path() },
        parametersMapper = { child ->
            when (val config = child.configuration) {
                is SibersPokedexRootComponent.Config.Item -> mapOf("id" to config.pokemon.id.toString())
                is SibersPokedexRootComponent.Config.Items -> emptyMap()
            }
        },
    )

    private fun child(
        config: SibersPokedexRootComponent.Config,
        componentContext: ComponentContext
    ): SibersPokedexRootComponent.Child =
        when (config) {
            is SibersPokedexRootComponent.Config.Items -> Items(
                PokemonItemsComponentImpl(
                    componentContext = componentContext,
                    dependencies = scope.get(),
                    onClickPokemon = { pokemon ->
                        stackNavigation.pushNew(SibersPokedexRootComponent.Config.Item(pokemon))
                    }
                ),
            )

            is SibersPokedexRootComponent.Config.Item -> Item(
                PokemonItemComponentImpl(
                    componentContext = componentContext,
                    pokemon = config.pokemon,
                    onBack = { stackNavigation.pop() }
                )
            )
        }

    private fun getInitialStack(deepLinkUrl: Url?): List<SibersPokedexRootComponent.Config> {
        val (path, childUrl) = deepLinkUrl?.consumePathSegment()
            ?: return listOf(SibersPokedexRootComponent.Config.Items)

        return when (path) {
            pathSegmentOf<SibersPokedexRootComponent.Config.Items>() -> listOf(SibersPokedexRootComponent.Config.Items)
            pathSegmentOf<SibersPokedexRootComponent.Config.Item>() -> listOf(SibersPokedexRootComponent.Config.Items)
            else -> listOf(SibersPokedexRootComponent.Config.Items)
        }
    }

}