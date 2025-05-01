plugins {
    alias(libs.plugins.kotlin.kmp.convention)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.markettwits.siberspokedex.root.api"
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.components.pokedex.cloud.api)
            implementation(projects.components.pokedex.items.api)
            implementation(projects.components.pokedex.item.api)

            implementation(libs.decompose)
        }
    }
}