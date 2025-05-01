package com.markettwits.siberspokedex.item.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.markettwits.core_ui.items.components.progress.shimmer
import com.markettwits.core_ui.items.extensions.noRippleClickable
import com.markettwits.core_ui.items.image.imageRequestCrossfade

@Composable
internal fun PokemonItemImage(
    modifier: Modifier = Modifier,
    pokemonImageUrl: String,
    pokemonName: String
) {

    var isFullscreen by rememberSaveable() { mutableStateOf(false) }

    Box(
        modifier = modifier
            .padding(top = 16.dp)
            .size(200.dp)
            .shadow(8.dp, CircleShape)
            .background(
                MaterialTheme.colorScheme.surface,
                CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            targetState = isFullscreen,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "basic_transition"
        ) { targetState ->
            if (!targetState) {
                SubcomposeAsyncImage(
                    model = imageRequestCrossfade(pokemonImageUrl),
                    contentDescription = pokemonName,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(180.dp)
                        .padding(8.dp)
                        .noRippleClickable {
                            isFullscreen = true
                        },
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(48.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }, error = {
                        Box(
                            modifier = Modifier
                                .shimmer()
                                .background(MaterialTheme.colorScheme.outline)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){}
                    }
                )
            } else {
                PokemonFullscreenImage(
                    imageUrl = pokemonImageUrl,
                    onDismiss = {
                        isFullscreen = false
                    },
                )
            }
        }
    }
}