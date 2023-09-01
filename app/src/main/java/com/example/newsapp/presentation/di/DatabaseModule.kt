package com.example.newsapp.presentation.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.data.db.Dao
import com.example.newsapp.data.db.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(app:Application) : Database {
        return  Room.databaseBuilder(app,Database::class.java,"my-database").fallbackToDestructiveMigration().build()

    }

    @Provides
    @Singleton
    fun provideSaveDao(database: Database) : Dao{
        return database.saveDao()
    }
}