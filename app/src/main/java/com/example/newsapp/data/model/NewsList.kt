package com.example.newsapp.data.model


import com.google.gson.annotations.SerializedName

data class NewsList(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)