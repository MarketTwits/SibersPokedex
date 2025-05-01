plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.kmp.convention)
}
android {
    namespace = "com.markettwits.siberspokedex.cloud.impl"
}

kotlin{
    sourceSets{
        jvmMain.dependencies{
            implementation(libs.ktor.client.okhttp)
        }
        androidMain.dependencies{
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(projects.components.pokedex.cloud.api)

            implementation(libs.ktor.client.json)
            implementation(libs.ktor.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.koin.core)
        }
    }
}
