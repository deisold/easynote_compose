package com.dirkeisold.easynotecompose.di

import com.dirkeisold.easynotecompose.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel() }
}