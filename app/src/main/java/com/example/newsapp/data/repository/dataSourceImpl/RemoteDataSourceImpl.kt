package com.example.newsapp.data.repository.dataSourceImpl

import com.example.newsapp.data.model.NewsList
import com.example.newsapp.data.repository.dataSource.RemoteDataSource
import com.example.newsapp.data.service.ServicesNews
import retrofit2.Response

class RemoteDataSourceImpl(
    private  val servicesNews: ServicesNews,

) : RemoteDataSource {
    override suspend fun getNews(country:String,page:Int): Response<NewsList> {
        return servicesNews.getNews(country,page)
    }

    override suspend fun searchNews(
        country: String,
        page: Int,
        searchQuery: String
    ): Response<NewsList> {
        return servicesNews.searchNews(country,page,searchQuery)
    }
}