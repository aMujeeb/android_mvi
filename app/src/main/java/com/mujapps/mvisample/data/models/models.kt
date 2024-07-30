package com.mujapps.mvisample.data.models

data class NewsArticle(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    val source: NewsSource
)

data class NewsSource(
    val id: String,
    val name: String
)

data class NewsSourceBaseResponse(
    val status: String,
    var totalResults: Int = 0,
    var articles: List<NewsArticle> = emptyList()
)