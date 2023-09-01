package com.example.newsapp.presentation.di

import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecase.DeleteNewsUseCase
import com.example.newsapp.domain.usecase.GetNewsUseCase
import com.example.newsapp.domain.usecase.GetSaveNewsUseCase
import com.example.newsapp.domain.usecase.SaveNewsUseCase
import com.example.newsapp.domain.usecase.SearchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

        @Provides
        @Singleton
        fun provideGetNews(
            repository: NewsRepository
        ): GetNewsUseCase {
            return GetNewsUseCase(repository)
        }

        @Provides
        @Singleton
        fun providesSearchNews(
            repository: NewsRepository
        ): SearchNewsUseCase {
            return SearchNewsUseCase(repository)
        }


        @Provides
        @Singleton
        fun provideSaveNews(repository: NewsRepository) : SaveNewsUseCase {
            return SaveNewsUseCase(repository)
        }

        @Provides
        @Singleton
        fun provideGetSaveNews(repository: NewsRepository): GetSaveNewsUseCase {
            return  GetSaveNewsUseCase(repository)
        }

        @Provides
        @Singleton
        fun providesDelete(repository: NewsRepository) : DeleteNewsUseCase{
            return DeleteNewsUseCase(repository)
        }

    }
