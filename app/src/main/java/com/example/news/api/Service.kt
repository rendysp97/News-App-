package com.example.news.api

import com.example.news.model.NewsResp
import com.example.news.util.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {


    @GET("v2/top-headlines")
    suspend fun getHeadline(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = Constant.API_KEY
    ): Response<NewsResp>


}