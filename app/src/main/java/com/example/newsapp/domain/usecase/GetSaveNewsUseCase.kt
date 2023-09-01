package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSaveNewsUseCase(private val repo:NewsRepository){

    fun execute() : Flow<List<Article>> = repo.getSaveNews()

}