package com.markettwits.siberspokedex.item.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddAlert
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.markettwits.core_ui.items.theme.FontNunito
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import kotlinx.coroutines.delay

@Composable
internal fun PokemonStatCard(pokemon: Pokemon) {
    var animateStats by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        delay(500)
        animateStats = true
    }

    Card(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Stats",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = FontNunito.bold(),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            pokemon.stats.forEach { stat ->
                val progress by animateFloatAsState(
                    targetValue = if (animateStats) stat.baseStat / 255f else 0f,
                    animationSpec = tween(durationMillis = 1000)
                )

                val statName = when (stat.stat.name) {
                    "hp" -> "HP"
                    "attack" -> "Attack"
                    "defense" -> "Defense"
                    "special-attack" -> "Sp. Attack"
                    "special-defense" -> "Sp. Defense"
                    "speed" -> "Speed"
                    else -> stat.stat.name.capitalize()
                }

                val statIcon = when (stat.stat.name) {
                    "hp" -> Icons.Rounded.Favorite
                    "defense" -> Icons.Rounded.Shield
                    "speed" -> Icons.Rounded.Speed
                    "attack" -> Icons.Rounded.AddAlert
                    else -> null
                }

                PokemonStatProgressBar(
                    statName = statName,
                    statValue = stat.baseStat,
                    progress = progress,
                    icon = statIcon,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}