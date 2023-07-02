package com.example.news.model


import com.google.gson.annotations.SerializedName

data class NewsResp(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)