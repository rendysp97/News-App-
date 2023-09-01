package com.example.newsapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.domain.usecase.DeleteNewsUseCase
import com.example.newsapp.domain.usecase.GetNewsUseCase
import com.example.newsapp.domain.usecase.GetSaveNewsUseCase
import com.example.newsapp.domain.usecase.SaveNewsUseCase
import com.example.newsapp.domain.usecase.SearchNewsUseCase

class NewsFactory(
    private val app: Application,
    private  val getNewsUseCase: GetNewsUseCase,
    private val searchNewsUseCase: SearchNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSaveNewsUseCase: GetSaveNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app,getNewsUseCase,searchNewsUseCase,saveNewsUseCase,getSaveNewsUseCase,deleteNewsUseCase) as T
    }
}