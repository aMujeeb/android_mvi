package com.mujapps.mvisample.domain

import com.mujapps.mvisample.data.repository.ArticlesRepository
import com.mujapps.mvisample.data.models.NewsArticle
import com.mujapps.mvisample.modal.DisplayNewsArticle
import com.mujapps.mvisample.utils.ApiResource
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ArticlesUseCase(private val artRepo: ArticlesRepository) {

    suspend fun getSourceBasedArticles(source: String): Flow<ApiResource<List<DisplayNewsArticle>>> = flow {
        try {
            emit(ApiResource.Loading)
            val response = artRepo.getSourceBaseArticles(source)
            if (response.status == "ok") {
                emit(ApiResource.Success(response.articles.map {
                    DisplayNewsArticle(
                        mAuthor = it.author ?: "- -",
                        mTitle = it.title ?: "- -",
                        mDescription = it.description ?: "- -",
                        mImageUrl = it.urlToImage ?: "-",
                        mDateTime = it.publishedAt ?: "- -",
                    )
                }))
            } else {
                emit(ApiResource.Error("Un-Known Error Pleas try again"))
            }
        } catch (e: ClientRequestException) {
            emit(ApiResource.Error(e.message))
        } catch (e: IOException) {
            emit(ApiResource.Error(e.message))
        }
    }
}