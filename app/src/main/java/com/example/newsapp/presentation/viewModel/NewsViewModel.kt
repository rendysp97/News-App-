package com.example.newsapp.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsList
import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.usecase.DeleteNewsUseCase
import com.example.newsapp.domain.usecase.GetNewsUseCase
import com.example.newsapp.domain.usecase.GetSaveNewsUseCase
import com.example.newsapp.domain.usecase.SaveNewsUseCase
import com.example.newsapp.domain.usecase.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val app:Application,
    private  val getNewsUseCase: GetNewsUseCase,
    private val searchNewsUseCase: SearchNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSaveNewsUseCase: GetSaveNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase
) : AndroidViewModel(app) {

    val headlineNews: MutableLiveData<Resource<NewsList>> = MutableLiveData()

    fun getNews(country:String,page:Int) = viewModelScope.launch(Dispatchers.IO) {

        headlineNews.postValue(Resource.Loading())

        try {

            if(isNetworkReady(app)) {

                val result = getNewsUseCase.execute(country,page)

                headlineNews.postValue(result)
            }else {
                headlineNews.postValue(Resource.Error("INTERNET NOT AVAILABLE"))
            }
        }catch (err:Exception) {
                headlineNews.postValue(Resource.Error(err.message.toString()))
        }

    }

    fun isNetworkReady(context:Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }
    }

    val search:MutableLiveData<Resource<NewsList>> = MutableLiveData()

    fun searchedNews(
        country: String,
        page: Int,
        querySearch:String
    )  = viewModelScope.launch {
        try {
            search.postValue(Resource.Loading())
            if(isNetworkReady(app)) {
                val result = searchNewsUseCase.execute(country,page,querySearch)

                search.postValue(result)

            }else {
                search.postValue(Resource.Error("Internet Not Available"))
            }

        }catch (err:Exception) {
            search.postValue(Resource.Error("${err.message.toString()}"))
        }
    }


   fun saveNews(article: Article) = viewModelScope.launch(Dispatchers.IO) {
       saveNewsUseCase.execute(article)
   }


    fun getSaveNews() = liveData {
       getSaveNewsUseCase.execute().collect{
            emit(it)
        }
    }

    fun deleteNews(article: Article)  = viewModelScope.launch(Dispatchers.IO) {
        deleteNewsUseCase.execute(article)
    }

}