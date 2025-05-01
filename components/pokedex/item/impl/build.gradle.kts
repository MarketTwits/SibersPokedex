plugins {
    alias(libs.plugins.kotlin.kmp.convention)
    alias(libs.plugins.kotlin.kmp.compose.convention)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.markettwits.siberspokedex.item.impl"
}
kotlin {
    sourceSets {
        commonMain.dependencies {

            implementation(projects.components.pokedex.cloud.api)
            implementation(projects.components.pokedex.cloud.impl)
            implementation(projects.components.pokedex.item.api)
            implementation(projects.components.core.ui)
            implementation(projects.components.core.koin)
            implementation(libs.bundles.decompose.compose)
            implementation(projects.components.core.decompose)
            implementation(libs.koin.core)
        }
    }
}