package com.example.newsapp.data.repository.dataSourceImpl

import com.example.newsapp.data.db.Dao
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.dataSource.LocalDataSource

import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val dao: Dao
) : LocalDataSource {
    override suspend fun saveNews(article: Article) {
        dao.saveNews(article)
    }

    override fun getSaveNews(): Flow<List<Article>> {
        return dao.getSaveNews()
    }

    override suspend fun deleteNews(article: Article) {
        return dao.deleteNews(article)
    }


}