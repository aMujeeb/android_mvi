package com.mujapps.mvisample.data.repository

import com.mujapps.mvisample.data.service.ArticlesServices
import com.mujapps.mvisample.modal.NewsSourceBaseResponse

class ArticlesRepository(private val mArticlesServices: ArticlesServices) {

    suspend fun getSourceBaseArticles(source : String) : NewsSourceBaseResponse {
        return mArticlesServices.fetchTopHeadLines(source, "")
    }
}