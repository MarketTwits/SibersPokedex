package com.markettwits.siberspokedex.cloud.api

import com.markettwits.siberspokedex.cloud.cloud.api.PokedexApi
import com.markettwits.siberspokedex.cloud.mapper.PokedexNetworkMapper
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val pokedexApiModule = module {
    single<Json> {
        provideDefaultJson()
    }
    single<HttpClient> {
        // get base url form build config instead of hardcode
        provideDefaultHttpClient(get(), POKE_API_URL)
    }
    singleOf(::PokedexNetworkMapper)
    singleOf(::PokedexNetworkApi)
    singleOf(::PokedexApiImpl) bind PokedexApi::class
}

private const val POKE_API_URL = "https://pokeapi.co/api/v2/"

/**
 * @suppress SibersPokedex
 * Use default json instead of provider from core module, only use for this project
 */
fun provideDefaultJson(): Json =
    Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = false
    }

/**
 * @suppress SibersPokedex
 * Use default httpclient instead of provider from core module, only use for this project
 */
fun provideDefaultHttpClient(
    json: Json,
    baseUrl: String,
): HttpClient =
    HttpClient() {
        install(Logging) {
            level = LogLevel.INFO
        }

        install(ContentNegotiation) {
            json(json)
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 30000
            connectTimeoutMillis = 15000
            socketTimeoutMillis = 15000
        }

        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            url.takeFrom(
                URLBuilder()
                    .takeFrom(baseUrl)
                    .apply { encodedPath += url.encodedPath }
            )
        }
    }