package com.markettwits.siberspokedex.item.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon

internal fun String.capitalize(): String =
     this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }


// Helper function to get a color based on pokemon type
internal fun getTypeColor(type: String): Color {
    return when (type.lowercase()) {
        "normal" -> Color(0xFFA8A77A)
        "fire" -> Color(0xFFEE8130)
        "water" -> Color(0xFF6390F0)
        "electric" -> Color(0xFFF7D02C)
        "grass" -> Color(0xFF7AC74C)
        "ice" -> Color(0xFF96D9D6)
        "fighting" -> Color(0xFFC22E28)
        "poison" -> Color(0xFFA33EA1)
        "ground" -> Color(0xFFE2BF65)
        "flying" -> Color(0xFFA98FF3)
        "psychic" -> Color(0xFFF95587)
        "bug" -> Color(0xFFA6B91A)
        "rock" -> Color(0xFFB6A136)
        "ghost" -> Color(0xFF735797)
        "dragon" -> Color(0xFF6F35FC)
        "dark" -> Color(0xFF705746)
        "steel" -> Color(0xFFB7B7CE)
        "fairy" -> Color(0xFFD685AD)
        else -> Color(0xFF919AA2) // Default
    }
}

// Helper function to get a color based on stat type
internal fun getStatColor(statName: String): Color {
    return when (statName) {
        "HP" -> Color(0xFFFF5959)
        "Attack" -> Color(0xFFF5AC78)
        "Defense" -> Color(0xFFFAE078)
        "Sp. Attack" -> Color(0xFF9DB7F5)
        "Sp. Defense" -> Color(0xFFA7DB8D)
        "Speed" -> Color(0xFFFA92B2)
        else -> Color(0xFF919AA2) // Default
    }
}

@Composable
internal fun rememberPokemonDominantColor(pokemon: Pokemon): Color {
    if (pokemon.types.isNotEmpty()) {
        return getTypeColor(pokemon.types[0].type.name)
    }
    return MaterialTheme.colorScheme.primary
}