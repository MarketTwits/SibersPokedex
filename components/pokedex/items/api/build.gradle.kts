plugins {
    alias(libs.plugins.kotlin.kmp.convention)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.markettwits.siberspokedex.items.api"
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.components.pokedex.cloud.api)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.paging.common)
            implementation(libs.decompose)
            implementation(libs.mvikotlin)
        }
    }
}