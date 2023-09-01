package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.newsapp.data.model.Article
import com.example.newsapp.databinding.FragmentInfoBinding
import com.example.newsapp.presentation.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Info : Fragment() {

    lateinit var binding: FragmentInfoBinding
    private val viewModel:NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)

        val args:InfoArgs by navArgs()
        val article = args.selectedArticle



        binding.web.apply {
            webViewClient = WebViewClient()

            try {
                val articleUrl = article?.url
                if (articleUrl != null) {
                    loadUrl(articleUrl)
                }else{
                    Toast.makeText(requireContext(),"LINK NOT FOUND",Toast.LENGTH_LONG).show()
                }

            }catch(e:Exception) {
               Log.i("MYTAG",e.message.toString())
            }

        }

        binding.floating.setOnClickListener {
            viewModel.saveNews(article)
            Snackbar.make(it,"Success Save",Snackbar.LENGTH_LONG).show()
        }

    }
}