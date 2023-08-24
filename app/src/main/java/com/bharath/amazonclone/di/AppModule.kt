package com.bharath.amazonclone.di

import com.bharath.amazonclone.data.ProductsDataBase
import com.bharath.amazonclone.presentation.screen.other.ListVm
import com.bharath.amazonclone.presentation.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideHomeViewModel(lap:ProductsDataBase)= HomeViewModel(lap)
    @Singleton
    @Provides
    fun provideListViewModel(lap:ProductsDataBase)= ListVm(lap)

    @Singleton
    @Provides
    fun provideLaptopDatabase()= ProductsDataBase()
}