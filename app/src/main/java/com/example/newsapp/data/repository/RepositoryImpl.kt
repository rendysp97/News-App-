package com.example.newsapp.data.repository

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsList
import com.example.newsapp.data.repository.dataSource.LocalDataSource
import com.example.newsapp.data.repository.dataSource.RemoteDataSource
import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {

    fun convertResponseToResource(response: Response<NewsList>) : Resource<NewsList>{
        if(response.isSuccessful){
            response.body()?.let {
                return  Resource.Success(it)
            }
        }
        return  Resource.Error(response.message())
    }


    override suspend fun getNews(country:String,page:Int): Resource<NewsList> {
      return convertResponseToResource(remoteDataSource.getNews(country,page))
    }

    override suspend fun searchNews(
        country: String,
        page: Int,
        searchQuery: String
    ): Resource<NewsList> {
       return convertResponseToResource(remoteDataSource.searchNews(country, page, searchQuery))
    }


    override suspend fun saveNews(article: Article) {
        return localDataSource.saveNews(article)
    }

    override suspend fun deleteNews(article: Article) {
        return localDataSource.deleteNews(article)
    }

    override fun getSaveNews(): Flow<List<Article>> {
        return localDataSource.getSaveNews()
    }


}