plugins {
    alias(libs.plugins.kotlin.kmp.convention)
    alias(libs.plugins.kotlin.kmp.compose.convention)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.markettwits.siberspokedex.item.api"
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.components.pokedex.cloud.api)
            implementation(libs.decompose)
        }
    }
}