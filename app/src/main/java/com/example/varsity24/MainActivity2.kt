package com.example.varsity24

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.varsity24.Models.NewsModel.Articles
import com.example.varsity24.Models.NewsModel.news_results
import com.example.varsity24.News.NewsApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers



class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            NewsApiClient.buildService()
                .getNews("za", "b9a9cdbf6b4d4fd59ed1e8769902c787")
                .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    onResponse(response)
                }, { t ->
                    onFailure(t)
                })
        )

        val newsRecyclerView = findViewById<RecyclerView>(R.id.recyclerViewNews)
        val layoutManager = LinearLayoutManager(this)
        newsRecyclerView.layoutManager = layoutManager
    }



    private fun onResponse(response: news_results) {
        Log.d("[news]", response.toString())
        val articles: List<Articles> = response.articles
        Log.d("[news]", "Number of articles: ${articles.size}")

        for (article in articles) {
            Log.d("[news]", "Article Title: ${article.title}")
        }
        val newsRecyclerView = findViewById<RecyclerView>(R.id.recyclerViewNews)
        val newsAdapter = NewsAdapter(articles)
        newsRecyclerView.adapter = newsAdapter
    }



    private fun onFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
        t.printStackTrace()
    }
}