package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.NewsList
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.data.util.Resource

class SearchNewsUseCase(private val repo: NewsRepository) {

    suspend fun execute(country:String,page:Int,searchQuery:String) : Resource<NewsList> = repo.searchNews(country, page, searchQuery)


}