package com.markettwits.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import coil3.compose.setSingletonImageLoaderFactory
import com.markettwits.core.theme.components.asyncImageLoader
import com.markettwits.core.theme.components.systemColorPallet
import com.markettwits.core_ui.items.theme.LocalDarkOrLightTheme
import com.markettwits.core_ui.items.theme.Typography

@Composable
fun SibersPokedexTheme(
    content: @Composable (Boolean) -> Unit
) {
    val palette = systemColorPallet(systemIsDark = isSystemInDarkTheme())

    SibersPokedexTheme(
        isDark = isSystemInDarkTheme(),
        colorScheme = palette,
        content = { content(true) }
    )
}

@Composable
private fun SibersPokedexTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    colorScheme: ColorScheme,
    content: @Composable () -> Unit
) {
    setSingletonImageLoaderFactory { context ->
        context.asyncImageLoader()
    }

    MaterialTheme(
        shapes = Shapes(medium = RoundedCornerShape(size = 10.dp)),
        colorScheme = colorScheme,
        typography = Typography,
    ) {
        CompositionLocalProvider(LocalDarkOrLightTheme provides isDark, content = content)
    }
}

