package com.example.newsapp.presentation.di

import android.app.Application
import com.example.newsapp.domain.usecase.DeleteNewsUseCase
import com.example.newsapp.domain.usecase.GetNewsUseCase
import com.example.newsapp.domain.usecase.GetSaveNewsUseCase
import com.example.newsapp.domain.usecase.SaveNewsUseCase
import com.example.newsapp.domain.usecase.SearchNewsUseCase
import com.example.newsapp.presentation.viewModel.NewsFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun provideFactory(
        application: Application,
        getNewsUseCase: GetNewsUseCase,
        searchNewsUseCase: SearchNewsUseCase,
        saveNewsUseCase: SaveNewsUseCase,
        getSaveNewsUseCase: GetSaveNewsUseCase,
        deleteNewsUseCase: DeleteNewsUseCase


    ) : NewsFactory {
        return NewsFactory(application,getNewsUseCase,searchNewsUseCase,saveNewsUseCase,getSaveNewsUseCase,deleteNewsUseCase)
    }


}