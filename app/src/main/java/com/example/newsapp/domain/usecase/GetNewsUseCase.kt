package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.NewsList
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.data.util.Resource

class GetNewsUseCase(private val repository:NewsRepository ) {

    suspend fun execute(country:String,page:Int) : Resource<NewsList> = repository.getNews(country,page)

}