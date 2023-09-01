package com.example.newsapp.presentation.di

import com.example.newsapp.presentation.adaptor.RecycleAdaptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideMyAdaptor() : RecycleAdaptor {
        return RecycleAdaptor()
    }
}