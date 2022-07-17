package com.homework.myapplication.di

import android.content.Context
import com.homework.myapplication.data.datasource.AssetLoader
import com.homework.myapplication.data.datasource.HomeAssetDataSource
import com.homework.myapplication.data.datasource.HomeDataSource
import com.homework.myapplication.data.datasource.HomeRemoteDataSource
import com.homework.myapplication.data.network.MusinsaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    @AssetDataSource
    fun provideAssetDataSource(assetLoader: AssetLoader): HomeDataSource {
        return HomeAssetDataSource(assetLoader)
    }

    @Provides
    @Singleton
    @RemoteDataSource
    fun provideRemoteDataSource(api: MusinsaService): HomeDataSource {
        return HomeRemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideAssetLoader(@ApplicationContext context: Context): AssetLoader {
        return AssetLoader(context)
    }
}