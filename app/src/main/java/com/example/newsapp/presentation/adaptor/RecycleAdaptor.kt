package com.example.newsapp.presentation.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.model.Article
import com.example.newsapp.databinding.ListNewsItemBinding

class RecycleAdaptor : RecyclerView.Adapter<RecycleAdaptor.MyViewHolder>(){


    private val callback = object: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val binding = ListNewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)
    }

    inner class MyViewHolder(val binding:ListNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article){

            binding.apply {

                title.text = article.title
                author.text = article.author
                Glide.with(root.context).load(article.urlToImage).into(img)

            }

            binding.root.setOnClickListener {
                onItemClick?.let {
                    it(article)
                }
            }


        }

    }

    private var onItemClick:((Article) -> Unit)? = null

     fun setOnItemClick(listener:(Article) -> Unit) {
        onItemClick = listener
    }

}