package com.mujapps.mvisample.di

import com.mujapps.mvisample.data.repository.ArticlesRepository
import com.mujapps.mvisample.data.service.ArticlesServices
import com.mujapps.mvisample.domain.ArticlesUseCase
import org.koin.dsl.module

val articleModule = module {
    single<ArticlesServices> {
        ArticlesServices(get())
    }

    single<ArticlesRepository> {
        ArticlesRepository(get())
    }

    single<ArticlesUseCase> {
        ArticlesUseCase(get())
    }
}