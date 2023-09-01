package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsList
import com.example.newsapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(country:String,page:Int) : Resource<NewsList>
    suspend fun searchNews(country: String,page: Int ,searchQuery:String) : Resource<NewsList>
    suspend fun  saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSaveNews() : Flow<List<Article>>

}