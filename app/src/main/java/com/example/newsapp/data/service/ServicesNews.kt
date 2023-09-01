package com.example.newsapp.data.service

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.NewsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServicesNews {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        country:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY
    ) : Response<NewsList>

    @GET("v2/top-headlines")
    suspend fun searchNews(
        @Query("country")
        country:String,
        @Query("page")
        page:Int,
        @Query("q")
        querySearch:String,
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY
    ) : Response<NewsList>
}