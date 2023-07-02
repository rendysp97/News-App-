package com.example.news.ui.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.model.NewsResp
import com.example.news.repository.NewsRepo
import com.example.news.util.Status
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepo
) : ViewModel() {

        val breakingNews: MutableLiveData<Status<NewsResp>> = MutableLiveData()
        var breakingNewsPage = 1

        init {
            getBreakingNews("us")
        }

        fun getBreakingNews(countryCode: String) = viewModelScope.launch {
            breakingNews.postValue(Status.Loading())
            val response = newsRepository.getHeadline(countryCode, breakingNewsPage)
            breakingNews.postValue(handleBreakingNewsResponse(response))
        }

        private fun handleBreakingNewsResponse(response: Response<NewsResp>) : Status<NewsResp> {
            if(response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    return Status.Success(resultResponse)
                }
            }
            return Status.Error(response.message())
        }
}