package com.example.newsapp.data.repository.dataSource

import com.example.newsapp.data.db.Dao
import com.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveNews(article: Article)
    fun getSaveNews() : Flow<List<Article>>
    suspend fun deleteNews(article: Article)
}