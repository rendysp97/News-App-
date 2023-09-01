package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.repository.NewsRepository

class DeleteNewsUseCase(private val repo: NewsRepository) {

    suspend fun execute(article: Article) = repo.deleteNews(article)
}