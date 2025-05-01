package com.markettwits.siberspokedex.item.components

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.markettwits.core_ui.items.screens.AdaptivePane
import com.markettwits.core_ui.items.theme.FontNunito
import com.markettwits.siberspokedex.item.api.PokemonItemComponent
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonItemPane(
    component: PokemonItemComponent,
    modifier: Modifier = Modifier
) {

    val state by component.state.collectAsState()

    val scrollState = rememberScrollState()

    var showContent by remember { mutableStateOf(false) }

    val backgroundAlpha by animateFloatAsState(
        targetValue = if (showContent) 0.8f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(key1 = true) {
        delay(200)
        showContent = true
    }

    val dominantColor = rememberPokemonDominantColor(state)

    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            dominantColor.copy(alpha = backgroundAlpha),
                            dominantColor.copy(alpha = backgroundAlpha * 0.8f),
                            dominantColor.copy(alpha = backgroundAlpha * 0.5f),
                            dominantColor.copy(alpha = backgroundAlpha * 0.3f),
                            MaterialTheme.colorScheme.primary
                        )
                    )
                )
        )
        AdaptivePane {
            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = component::onClickGoBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
                AnimatedVisibility(
                    visible = showContent,
                    enter = fadeIn(tween(1000)) + slideInVertically(
                        initialOffsetY = { it / 2 },
                        animationSpec = tween(700)
                    ),
                    exit = fadeOut()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PokemonItemImage(
                            pokemonName = state.name,
                            pokemonImageUrl = state.image
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "#${state.id} ${state.name.capitalize()}",
                            fontSize = 28.sp,
                            fontFamily = FontNunito.extraBold(),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            state.types.forEach { pokemonType ->
                                PokemonTypeChip(pokemonType)
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        PokemonPhysicalAttributesCard(state)
                        Spacer(modifier = Modifier.height(16.dp))
                        PokemonStatCard(state)
                    }
                }
            }
        }
    }
}