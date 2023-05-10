package io.ak1.nytimes.di

import com.example.trycartask.data.repos.PostsRepository
import com.example.trycartask.ui.screens.home.PostsViewModel

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module



/**
 * modules for dependency injection where [single] represents singleton class
 */

var networkModule = module {
    single { getLogInterceptor() }
    single { returnRetrofit(get()) }
    single { getApi(get()) }
}

var databaseModule = module {
    single { getDb(androidApplication()) }
}

var repoModule = module {
    single { getCoroutineContext() }
    single { PostsRepository(get(), get(), get(), get()) }
}

var viewModelModule = module {
    viewModel { PostsViewModel(get()) }
}