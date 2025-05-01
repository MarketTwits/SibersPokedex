package com.markettwits.core_ui.items.font

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.preloadFont
import siberspokedex.components.core.ui.generated.resources.*

@Composable
fun PreloadFontResources(onFontReady: @Composable () -> Unit) {
    var areFontsReady by remember { mutableStateOf(false) }

    val font1 by preloadFont(Res.font.nunito_light)
    val font2 by preloadFont(Res.font.nunito_light)
    val font3 by preloadFont(Res.font.nunito_regular)
    val font4 by preloadFont(Res.font.nunito_medium)
    val font5 by preloadFont(Res.font.nunito_bold)
    val font6 by preloadFont(Res.font.nunito_semi_bold)
    val font7 by preloadFont(Res.font.nunito_extra_bold)

    LaunchedEffect(font1, font2, font3, font4, font5, font6, font7) {
        if (font1 != null && font2 != null && font3
            != null && font4 != null && font5
            != null && font6 != null && font7 != null
        ) {
            areFontsReady = true
        }
    }

    if (!areFontsReady) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    } else {
        onFontReady()
    }
}