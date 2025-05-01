package com.markettwits.siberspokedex.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.defaultComponentContext
import com.markettwits.core.theme.SibersPokedexTheme
import com.markettwits.siberspokedex.root.SibersPokedexRootComponentImpl
import com.markettwits.siberspokedex.root.SibersPokedexRootContent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        startKoin {
            androidContext(applicationContext)
            val defaultComponentContext = defaultComponentContext()
            val root = SibersPokedexRootComponentImpl(componentContext = defaultComponentContext)
            setContent {
                SibersPokedexTheme {
                    SystemBarColors(isSystemInDarkTheme())
                    SibersPokedexRootContent(component = root)
                }
            }
        }
    }

    override fun onDestroy() {
        stopKoin()
        super.onDestroy()
    }
}
