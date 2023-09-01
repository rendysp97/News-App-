package com.example.newsapp.presentation.di

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.service.ServicesNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Provides
    @Singleton
    fun provideRetro():Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BuildConfig.BASE_URL).build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit):ServicesNews {
        return retrofit.create(ServicesNews::class.java)
    }


}