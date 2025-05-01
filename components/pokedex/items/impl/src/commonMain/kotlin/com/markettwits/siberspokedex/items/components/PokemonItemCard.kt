package com.markettwits.siberspokedex.items.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.markettwits.core_ui.items.image.imageRequestCrossfade
import com.markettwits.core_ui.items.theme.SportSouceColor
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import com.markettwits.siberspokedex.cloud.cloud.model.getStatByName

@Composable
internal fun PokemonItemCard(
    modifier: Modifier = Modifier,
    isHighlighted: Boolean,
    pokemon: Pokemon,
    onClick: (Pokemon) -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "highlightAnimation")

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isHighlighted) 1.03f else 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(700),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scaleAnimation"
    )

    val backgroundColor = if (isHighlighted)
        SportSouceColor.SportSouceRegistryOpenGreen
    else
        MaterialTheme.colorScheme.primary

    val borderStroke = if (isHighlighted)
        BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
    else
        null

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .scale(if (isHighlighted) scale else 1f)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable { onClick(pokemon) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isHighlighted) 8.dp else 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        border = borderStroke,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .width(130.dp)
                .height(260.dp)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(0.7f)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                AsyncImage(
                    model = imageRequestCrossfade(pokemon.image),
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))
                pokemon.let {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        it.types.take(2).forEach { pokemonType ->
                            PokemonTypeChip(type = pokemonType.type.name)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        StatBar(
                            name = "HP",
                            value = it.getStatByName("hp"),
                            maxValue = 150,
                            color = Color(0xFFFF5959),
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        StatBar(
                            name = "ATK",
                            value = it.getStatByName("attack"),
                            maxValue = 150,
                            color = Color(0xFFF5AC78),
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        StatBar(
                            name = "DEF",
                            value = it.getStatByName("defense"),
                            maxValue = 150,
                            color = Color(0xFFFAE078),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}