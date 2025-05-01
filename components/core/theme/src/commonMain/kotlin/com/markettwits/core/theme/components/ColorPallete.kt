package com.markettwits.core.theme.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
internal fun systemColorPallet(
    systemIsDark: Boolean = isSystemInDarkTheme(),
): ColorScheme = if (systemIsDark) DarkColorScheme.toAnimatePallet() else LightColorScheme.toAnimatePallet()