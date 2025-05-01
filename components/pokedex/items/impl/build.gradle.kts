
plugins {
    alias(libs.plugins.kotlin.kmp.convention)
    alias(libs.plugins.kotlin.kmp.compose.convention)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.markettwits.siberspokedex.items.impl"
}
kotlin {
    sourceSets {
        commonMain.dependencies {

            implementation(projects.components.core.cache)
            implementation(projects.components.core.ui)
            implementation(projects.components.core.koin)
            implementation(projects.components.core.log)
            implementation(projects.components.core.paging)
            implementation(projects.components.core.decompose)
            implementation(projects.components.pokedex.items.api)
            implementation(projects.components.pokedex.cloud.api)
            implementation(projects.components.pokedex.cloud.impl)
            implementation(compose.components.resources)
            implementation(libs.bundles.decompose.compose)
            implementation(libs.bundles.mviKotlin)
            implementation(libs.koin.core)
        }
    }
}