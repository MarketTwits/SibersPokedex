import com.markettwits.siberspokedex.sources.ApkConfig

plugins {
    id("android.application.convention")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = ApkConfig.APPLICATION_ID

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        defaultConfig {
            androidResources {
                localeFilters += listOf("en")
            }
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
dependencies {
    implementation(projects.components.core.activityholder)
    implementation(projects.components.core.cache)
    implementation(projects.components.core.theme)
    implementation(projects.components.core.ui)

    implementation(projects.components.pokedex.root.impl)
    implementation(projects.components.pokedex.root.api)
    
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.compose.activity)
    implementation(libs.bundles.decompose.compose)
    implementation(libs.koin.android)

}
