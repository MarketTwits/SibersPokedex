package com.markettwits.siberspokedex.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.markettwits.siberspokedex.item.components.PokemonItemPane
import com.markettwits.siberspokedex.items.components.PokemonItemsPane

@Composable
fun SibersPokedexRootContent(
    component: SibersPokedexRootComponent,
    modifier: Modifier = Modifier
) {
    Children(
        modifier = modifier,
        stack = component.childStack,
        animation = stackAnimation(fade() + scale()),
    ) {
        Column(modifier = Modifier)
        {
            when (val child = it.instance) {
                is SibersPokedexRootComponent.Child.Items -> PokemonItemsPane(component = child.component)
                is SibersPokedexRootComponent.Child.Item -> PokemonItemPane(component = child.component)
            }
        }

    }

}

