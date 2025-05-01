package com.markettwits.siberspokedex.app.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.badoo.reaktive.coroutinesinterop.asScheduler
import com.badoo.reaktive.scheduler.overrideSchedulers
import com.markettwits.cahce.InStorageCacheDirectory
import com.markettwits.cahce.InStorageFileDirectory
import com.markettwits.core.theme.SibersPokedexTheme
import com.markettwits.initKoin
import com.markettwits.siberspokedex.root.SibersPokedexRootComponentImpl
import com.markettwits.siberspokedex.root.SibersPokedexRootContent
import kotlinx.coroutines.Dispatchers
import java.io.File
import java.util.*


fun main() {

    Locale.setDefault(Locale("ru", "RUS"))

    overrideSchedulers(main = Dispatchers.Main::asScheduler)

    InStorageCacheDirectory.path = File(System.getProperty("java.io.tmpdir")).absolutePath
    InStorageFileDirectory.path = File(System.getProperty("java.io.tmpdir")).absolutePath

    val lifecycle = LifecycleRegistry()
    val context = DefaultComponentContext(lifecycle)

    initKoin {
        val root = runOnUiThread { SibersPokedexRootComponentImpl(context) }
        application {
            val windowState = rememberWindowState()
            LifecycleController(lifecycle, windowState)
            Window(
                title = "SibersPokedex",
                onCloseRequest = { exitApplication() }
            ) {
                SibersPokedexTheme {
                    SibersPokedexRootContent(root)
                }
            }
        }
    }
}
