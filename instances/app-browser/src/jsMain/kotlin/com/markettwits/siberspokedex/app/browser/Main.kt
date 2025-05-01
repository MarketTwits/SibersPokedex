package com.markettwits.siberspokedex.app.browser

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.webhistory.withWebHistory
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.markettwits.core.theme.SibersPokedexTheme
import com.markettwits.core_ui.items.font.PreloadFontResources
import com.markettwits.initKoin
import com.markettwits.siberspokedex.root.SibersPokedexRootComponent
import com.markettwits.siberspokedex.root.SibersPokedexRootComponentImpl
import com.markettwits.siberspokedex.root.SibersPokedexRootContent
import com.markettwits.siberspokedex.root.Url
import kotlinx.browser.document
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class, ExperimentalDecomposeApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()
    InitStorageForWeb()
    initKoin {
        val root: SibersPokedexRootComponent = withWebHistory { stateKeeper, deepLink ->
            SibersPokedexRootComponentImpl(
                componentContext = DefaultComponentContext(lifecycle = lifecycle),
                deepLinkUrl = deepLink?.let(::Url),
            )
        }
        lifecycle.attachToDocument()
        onWasmReady {
            ComposeViewport(document.body ?: return@onWasmReady) {
                PreloadFontResources({
                    SibersPokedexTheme {
                        SibersPokedexRootContent(root)
                    }
                })
            }
        }
    }
}