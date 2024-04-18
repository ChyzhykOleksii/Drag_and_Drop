package com.conscensia.draganddrop.di

import com.conscensia.draganddrop.data.repositories.IPointRepository
import com.conscensia.draganddrop.data.repositories.PointRepository
import com.conscensia.draganddrop.data.sourse.ISharedPreferencesDataSource
import com.conscensia.draganddrop.data.sourse.SharedPreferencesDataSource
import com.conscensia.draganddrop.viewmodels.DragAndDropViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    includes(appModule)
    single<ISharedPreferencesDataSource> { SharedPreferencesDataSource(androidContext()) }
    single<IPointRepository> { PointRepository(get(), get()) }
    viewModel { DragAndDropViewModel(get()) }
}