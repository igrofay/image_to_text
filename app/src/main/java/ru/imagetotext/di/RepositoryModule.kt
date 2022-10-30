package ru.imagetotext.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import ru.imagetotext.data.repos.FeaturesRepositoryImpl
import ru.imagetotext.domain.repos.FeaturesRepository


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideFeaturesRepository(client: HttpClient): FeaturesRepository {
        return FeaturesRepositoryImpl(client)
    }
}