package com.aydingani.footballnews

class NewsRepository {
    suspend fun getFootballNews(apiKey: String): List<NewsItem> {
        return try {
            val response = RetrofitInstance.api.getFootballNews(
                query = "football",
                apiKey = apiKey
            )
            if (response.isSuccessful) {
                response.body()?.articles?.map { article ->
                    NewsItem(
                        title = article.title,
                        description = article.description ?: "No description",
                        imageUrl = article.urlToImage,
                        date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            .format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                                .parse(article.publishedAt) ?: ""
                            )
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}