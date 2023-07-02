package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.news.databinding.ActivityMainBinding
import com.example.news.db.NewsDatabase

import com.example.news.repository.NewsRepo
import com.example.news.ui.view.NewsViewModel
import com.example.news.ui.view.ViewModelFactory


class MainActivity : AppCompatActivity() {
    lateinit var viewModel:NewsViewModel
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.bottomNavigationView.setupWithNavController(binding.newsNavHostFragment.findNavController())

        val newsRepository = NewsRepo(NewsDatabase(this))
        val viewModelProviderFactory = ViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]
    }
}