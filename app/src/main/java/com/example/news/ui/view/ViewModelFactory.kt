package com.example.news.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.news.repository.NewsRepo

class ViewModelFactory(
    val repo: NewsRepo
)  : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return   NewsViewModel(repo) as T
        }
        throw IllegalArgumentException("ERROR")
    }

}