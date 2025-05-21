package com.aydingani.footballnews

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.BuildConfig
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val repository = NewsRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get your API key from https://newsapi.org
        val apiKey = BuildConfig.NEWS_API_KEY

        lifecycleScope.launch {
            val newsList = repository.getFootballNews(apiKey)
            setupRecyclerView(newsList)
        }
    }

    private fun setupRecyclerView(newsList: List<NewsItem>) {
        val adapter = NewsAdapter(newsList)
        binding.newsRecyclerView.adapter = adapter
    }
}

