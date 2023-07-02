package com.example.news.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

import android.view.View

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.MainActivity
import com.example.news.R
import com.example.news.adapter.NewsAdapter
import com.example.news.databinding.FragmentHeadlineBinding
import com.example.news.ui.view.NewsViewModel
import com.example.news.util.Status



class Headline : Fragment(R.layout.fragment_headline) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding:FragmentHeadlineBinding

    val TAG = "Headline"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Status.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Status.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->

                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Status.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility= View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}