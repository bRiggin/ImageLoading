package com.briggin.average.property.blockchainrbiggin

import com.briggin.average.property.blockchainrbiggin.core.DogDataSource
import com.briggin.average.property.blockchainrbiggin.data.DogApi
import com.briggin.average.property.blockchainrbiggin.data.DogRepository
import com.briggin.average.property.blockchainrbiggin.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val koinModule = module {

    viewModel { MainViewModel(get()) }

    factory<DogDataSource> { DogRepository(get()) }

    factory<DogApi> {
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/image/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}