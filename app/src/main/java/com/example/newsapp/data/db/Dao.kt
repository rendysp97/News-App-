package com.example.newsapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNews(article: Article)

    @Query("SELECT * FROM table_article ")
    fun getSaveNews() : Flow<List<Article>>

    @Delete
    fun deleteNews(article: Article)
}