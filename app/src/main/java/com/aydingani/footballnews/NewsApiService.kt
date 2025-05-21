package com.aydingani.footballnews

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun getFootballNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsApiResponse>
}

data class NewsApiResponse(
    val articles: List<NewsArticle>
)

data class NewsArticle(
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String
)