package com.example.newsapp.data.repository.dataSource

import com.example.newsapp.data.model.NewsList
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getNews(country:String,page:Int) : Response<NewsList>
    suspend fun searchNews(country: String,page: Int,searchQuery:String):Response<NewsList>

}