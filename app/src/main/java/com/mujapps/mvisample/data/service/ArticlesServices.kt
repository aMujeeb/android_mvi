package com.mujapps.mvisample.data.service

import com.mujapps.mvisample.data.models.NewsSourceBaseResponse
import com.mujapps.mvisample.utils.MviAppConstants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path

class ArticlesServices(private val mHttpClient: HttpClient) {

    suspend fun fetchTopHeadLines(source : String, apiKey : String) : NewsSourceBaseResponse {
        return mHttpClient.get(MviAppConstants.NEWS_BASE_URL) {
            url {
                path("v2/top-headlines")
                parameter("sources", source)
                parameter("apiKey", apiKey)
            }
        }.body()
    }
}