package com.mujapps.mvisample.di

import com.mujapps.mvisample.ui.views.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vieModelsModule = module {
    viewModel {
        MainViewModel(get())
    }
}