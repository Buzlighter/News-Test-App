package com.test.newsapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.newsapp.R
import com.test.newsapp.model.Article
import com.test.newsapp.util.DatePattern

class NewsAdapter(newsList: List<Article?>): RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    private val newsList = ArrayList(newsList)
    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(article: Article?)
    }

    fun setOnItemClickListener(itemListener: OnItemClickListener) {
        listener = itemListener
    }

    inner class NewsHolder(itemView: View, itemListener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
        val ivArticleImage: ImageView = itemView.findViewById(R.id.image)
        val tvTitle: TextView = itemView.findViewById(R.id.title)
        val tvDescription: TextView = itemView.findViewById(R.id.description)
        val tvSource: TextView = itemView.findViewById(R.id.source)
        val tvPublishedDate: TextView = itemView.findViewById(R.id.published_at)

        init {
            itemView.setOnClickListener {
                itemListener.onItemClick(newsList[bindingAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val newsView = inflater.inflate(R.layout.news_item, parent, false)

        return NewsHolder(newsView, listener)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val article = newsList[position]
        holder.itemView.apply {
            Glide.with(this).load(article?.image).centerCrop().into(holder.ivArticleImage)
            holder.tvTitle.text = article?.title
            holder.tvDescription.text = article?.description
            holder.tvSource.text = article?.source?.name
            holder.tvPublishedDate.text = DatePattern.editDate(article?.publishedDate)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}