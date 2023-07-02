package com.example.news.repository

import com.example.news.api.RetrofitInst
import com.example.news.db.NewsDatabase

class NewsRepo(
    val db: NewsDatabase
) {
    suspend fun getHeadline(countryCode: String, pageNumber: Int) =
        RetrofitInst.api.getHeadline(countryCode, pageNumber)
}