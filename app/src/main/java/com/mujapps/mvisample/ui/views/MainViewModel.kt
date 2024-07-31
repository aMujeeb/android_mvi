package com.mujapps.mvisample.ui.views

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mujapps.mvisample.domain.ArticlesUseCase
import com.mujapps.mvisample.utils.ApiResource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(private val mNewsUseCase: ArticlesUseCase) : ViewModel() {

    val mUserIntent = Channel<NewsIntent>(Channel.UNLIMITED)
    var mState = mutableStateOf<NewsHeadLinesState>(NewsHeadLinesState.Idle)
        private set

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            mUserIntent.consumeAsFlow().collect { collector ->
                when (collector) {
                    is NewsIntent.FetchTopHeadLines -> fetchNewsHeadLines()
                }
            }
        }
    }

    private fun fetchNewsHeadLines() {
        viewModelScope.launch {
            mState.value = NewsHeadLinesState.Loading
            mNewsUseCase.getSourceBasedArticles("techcrunch").onEach { response ->
                when(response) {
                    is ApiResource.Success -> {
                        response.data?.let {
                            mState.value = NewsHeadLinesState.NewsHeadLines(it)
                        }
                    }

                    is ApiResource.Error -> {
                        mState.value = NewsHeadLinesState.Error(response.errorMessage ?: "Error Try Again")
                    }
                }
            }.launchIn(this)
        }
    }
}