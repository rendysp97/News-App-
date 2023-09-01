package com.example.newsapp.presentation.di

import com.example.newsapp.data.db.Dao
import com.example.newsapp.data.repository.dataSource.LocalDataSource
import com.example.newsapp.data.repository.dataSourceImpl.LocalDataSourceImpl
import com.example.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideNewsLocalDataSource(dao: Dao) : LocalDataSource{
        return LocalDataSourceImpl(dao)
    }

}