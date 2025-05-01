plugins {
    alias(libs.plugins.kotlin.kmp.convention)
    alias(libs.plugins.kotlin.kmp.compose.convention)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.markettwits.sportsauce.root.impl"
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.components.pokedex.root.api)

            implementation(projects.components.pokedex.items.api)
            implementation(projects.components.pokedex.items.impl)

            implementation(projects.components.pokedex.item.api)
            implementation(projects.components.pokedex.item.impl)

            implementation(projects.components.pokedex.cloud.api)

            implementation(projects.components.core.ui)
            implementation(projects.components.core.koin)
            implementation(projects.components.core.decompose)
            implementation(libs.bundles.decompose.compose)
            implementation(libs.koin.core)
        }
    }
}