enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://jitpack.io")
        maven("https://artifactory-external.vkpartner.ru/artifactory/maven/")
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://artifactory-external.vkpartner.ru/artifactory/maven/")
    }
}

rootProject.name = "SibersPokedex"
includeBuild("build-logic")

include(
    ":instances:app-android",
    ":instances:app-browser",
    ":instances:app-desktop",

    ":components:pokedex:cloud:api",
    ":components:pokedex:cloud:impl",

    ":components:pokedex:items:api",
    ":components:pokedex:items:impl",

    ":components:pokedex:item:api",
    ":components:pokedex:item:impl",

    ":components:pokedex:root:api",
    ":components:pokedex:root:impl",


    ":components:core:ui",
    ":components:core:theme",
    ":components:core:activityholder",
    ":components:core:log",
    ":components:core:cache",
    ":components:core:koin",
    ":components:core:paging",
    ":components:core:decompose",
)
