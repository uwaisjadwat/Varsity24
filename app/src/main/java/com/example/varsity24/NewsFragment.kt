package com.example.varsity24

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.varsity24.Models.NewsModel.Articles
import com.example.varsity24.Models.NewsModel.news_results
import com.example.varsity24.ApiClient.NewsApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable.add(
            NewsApiClient.buildService()
                .getNews("za", "e6870c60da5b44d4a083240aa9fc5820")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    onResponse(response)
                }, { t ->
                    onFailure(t)
                })
        )

        val newsRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewNews)
        val layoutManager = LinearLayoutManager(requireContext())
        newsRecyclerView.layoutManager = layoutManager
    }

    private fun onResponse(response: news_results) {
        Log.d("[news]", response.toString())
        val articles: List<Articles> = response.articles
        val newsRecyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerViewNews)
        val newsAdapter = NewsAdapter(articles)
        newsRecyclerView.adapter = newsAdapter
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
        t.printStackTrace()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}