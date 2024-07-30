package com.mujapps.mvisample.ui.views

sealed class NewsIntent {
    // Reflecting UI actions
    data object FetchTopHeadLines : NewsIntent() //In this example button click for get Top Headlines
}