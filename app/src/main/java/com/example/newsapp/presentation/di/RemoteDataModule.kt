package com.example.newsapp.presentation.di

import com.example.newsapp.data.repository.dataSource.RemoteDataSource
import com.example.newsapp.data.repository.dataSourceImpl.RemoteDataSourceImpl
import com.example.newsapp.data.service.ServicesNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(
        servicesNews: ServicesNews
    ) : RemoteDataSource {
        return RemoteDataSourceImpl(servicesNews)
    }

}