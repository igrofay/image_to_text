package ru.imagetotext.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val urlServer = "http://176.28.64.201:3436"
    @Provides
    @Singleton
    fun provideHttpClient() : HttpClient {
        return HttpClient(CIO){
            install(HttpTimeout) {
                requestTimeoutMillis = 90_000
                connectTimeoutMillis = 90_000
            }
            install(HttpRequestRetry) {
                exponentialDelay()
            }
            install(HttpCache)
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            expectSuccess = true
            defaultRequest{
                url(urlServer)
            }
        }
    }
}