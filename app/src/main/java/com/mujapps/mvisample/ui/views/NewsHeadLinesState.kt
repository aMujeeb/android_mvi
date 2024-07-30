package com.mujapps.mvisample.ui.views

import com.mujapps.mvisample.modal.DisplayNewsArticle

sealed class NewsHeadLinesState {

    data object Idle : NewsHeadLinesState()
    data object Loading : NewsHeadLinesState()
    data class NewsHeadLines(val headLines: List<DisplayNewsArticle>) : NewsHeadLinesState()
    data class Error(val error: String) : NewsHeadLinesState()
}