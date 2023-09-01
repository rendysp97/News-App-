package com.example.newsapp


import android.os.Bundle

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.util.Resource
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.presentation.adaptor.RecycleAdaptor
import com.example.newsapp.presentation.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class News : Fragment() {
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var  binding: FragmentNewsBinding
    @Inject
    lateinit var adaptor: RecycleAdaptor


    private  var country = "us"
    private var page= 1
    private var pages = 0
    private var isLast = false;
    private var isScroll = false
    private var isLoading = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)

        adaptor.setOnItemClick {
            val bundle = Bundle().apply {
                putSerializable("selected_article",it)
            }

            findNavController().navigate(
                R.id.action_news_to_info,
                bundle
            )
        }


        initRecycle()
        viewNewsList()
        onSearch()
    }

    private fun initRecycle() {
        adaptor = this@News.adaptor
        binding.listNews.apply {
            adapter = adaptor
            layoutManager = LinearLayoutManager(requireContext())
           addOnScrollListener(this@News.onScrollListener)
        }


    }


    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScroll = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.listNews.layoutManager as LinearLayoutManager
            val visibleItem = layoutManager.childCount
            val totalItem = layoutManager.itemCount
            val positionItem = layoutManager.findFirstVisibleItemPosition()

            val reachedEnd = positionItem + visibleItem >= totalItem

            val shouldPaginate = !isLoading && !isLast && reachedEnd && isScroll

            if(shouldPaginate){
                page++
                viewModel.getNews(country, page)
                isScroll = false
            }


        }
    }


    private fun viewNewsList() {
        viewModel.getNews(country,page)
        viewModel.headlineNews.observe(viewLifecycleOwner) { resp ->

            when (resp) {
                is Resource.Loading -> {
                    showLoad()
                }

                is Resource.Error -> {
                    hideLoad()
                    resp.message?.let {
                        Toast.makeText(requireContext(),"Error Occured",Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Success -> {
                    hideLoad()
                    resp.data?.let {
                        adaptor.differ.submitList(it.articles.toList())

                        if(it.totalResults / 20 == 0) {
                            pages = it.totalResults / 20

                        }else {
                            pages = it.totalResults / 20 + 1
                        }
                        isLast = page == pages

                    }
                }


            }
        }
    }

    private fun onSearch() {
        binding.search.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchedNews("us",page,query.toString())
                viewSearchList()
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    viewModel.searchedNews("us",page,newText.toString())
                    viewSearchList()

                }
                return false
            }

        })

        binding.search.setOnCloseListener(object :SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                initRecycle()
                viewNewsList()
                return false
            }

        })
    }


    private fun viewSearchList(){
       viewModel.search.observe(viewLifecycleOwner) {resp ->
           when(resp) {
               is Resource.Loading -> {
                   showLoad()
               }

               is Resource.Error -> {
                   hideLoad()
                   Toast.makeText(requireContext(),"Error Occured",Toast.LENGTH_LONG).show()
               }

               is Resource.Success -> {
                   hideLoad()

                   resp.data?.let {
                       adaptor.differ.submitList(it.articles.toList())

                       if(it.totalResults / 20 == 0) {
                           pages = it.totalResults/ 20
                       }else {
                           pages = it.totalResults/ 20 + 1
                       }
                       isLast = page == pages
                   }
               }

           }
       }
    }

    private fun showLoad() {
        isLoading = true
        binding.pg.visibility = View.VISIBLE
    }

    private  fun hideLoad() {
        isLoading = false
        binding.pg.visibility = View.GONE
    }

}
