package com.homework.myapplication.di

import com.homework.myapplication.data.datasource.HomeDataSource
import com.homework.myapplication.data.repository.HomeRepositoryImpl
import com.homework.myapplication.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        @AssetDataSource assetDataSource: HomeDataSource,
        @RemoteDataSource remoteDataSource: HomeDataSource
    ): HomeRepository {
        return HomeRepositoryImpl(assetDataSource, remoteDataSource)
    }
}