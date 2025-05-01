package com.markettwits.siberspokedex.item.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.markettwits.siberspokedex.cloud.cloud.model.PokemonType

@Composable
internal fun PokemonTypeChip(pokemonType: PokemonType) {
    val backgroundColor = getTypeColor(pokemonType.type.name)

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor,
        modifier = Modifier.Companion.padding(horizontal = 4.dp)
    ) {
        Text(
            text = pokemonType.type.name.capitalize(),
            color = Color.Companion.White,
            modifier = Modifier.Companion.padding(horizontal = 16.dp, vertical = 8.dp),
            fontWeight = FontWeight.Companion.Bold
        )
    }
}