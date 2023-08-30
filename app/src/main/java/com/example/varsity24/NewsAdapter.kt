package com.example.varsity24

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.varsity24.Models.NewsModel.Articles



class NewsAdapter(private val articles: List<Articles>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }



    override fun getItemCount(): Int {
        return articles.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
        private val publishedDate: TextView = itemView.findViewById(R.id.textViewPublishedAt)
        private val newsImage: ImageView = itemView.findViewById(R.id.imageViewArticle)






        fun bind(article: Articles) {
            titleTextView.text = article.title
            descriptionTextView.text = article.description
            publishedDate.text = article.publishedAt

            // Load and display the image using Glide
            Glide.with(itemView.context)
                .load(article.urlToImage)
                .into(newsImage)

        }
    }
}