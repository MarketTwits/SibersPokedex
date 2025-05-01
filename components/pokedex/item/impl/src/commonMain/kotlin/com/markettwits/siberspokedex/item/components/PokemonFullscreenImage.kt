package com.markettwits.siberspokedex.item.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.SubcomposeAsyncImage
import com.markettwits.core_ui.items.image.imageRequestCrossfade
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

/**
 * A fullscreen dialog that displays a Pokemon image with zoom and pan gestures.
 * Uses shared element transition principles for smooth animations.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun PokemonFullscreenImage(
    imageUrl: String,
    onDismiss: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(300)
    )

    val zoomState = rememberZoomState(maxScale = 25f)

    LaunchedEffect(key1 = true) {
        delay(50)
        visible = true
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        AnimatedContent(
            modifier = Modifier,
            transitionSpec = {
                fadeIn() togetherWith fadeOut()
            },
            targetState = true,
        ) { product ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = alpha * 0.9f))
                    .alpha(alpha)
            ) {
                SubcomposeAsyncImage(
                    model = imageRequestCrossfade(imageUrl),
                    contentDescription = "Fullscreen Pokemon",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .zoomable(zoomState = zoomState)
                        .fillMaxSize()
                )
                IconButton(
                    onClick = {
                        visible = false
                        kotlinx.coroutines.MainScope().launch {
                            delay(300)
                            onDismiss()
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(24.dp)
                        .alpha(alpha)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}