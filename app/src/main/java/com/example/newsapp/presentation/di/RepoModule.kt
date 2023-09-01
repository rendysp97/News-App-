package com.example.newsapp.presentation.di

import com.example.newsapp.data.repository.RepositoryImpl
import com.example.newsapp.data.repository.dataSource.LocalDataSource
import com.example.newsapp.data.repository.dataSource.RemoteDataSource
import com.example.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideRepo(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ) : NewsRepository {
        return RepositoryImpl(remoteDataSource,localDataSource)
    }

}