package com.mujapps.mvisample.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.SIMPLE
import okhttp3.Interceptor
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<HttpClient> {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                })
            }

            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }

            engine {
                config {
                    followRedirects(true)
                    connectTimeout(10, TimeUnit.SECONDS)
                    //addInterceptor(provideApiKeyInterceptor()) //Adding interceptors
                }
            }
        }
    }
}

//Returning an Interceptor
/*fun provideApiKeyInterceptor(): Interceptor {

} */
