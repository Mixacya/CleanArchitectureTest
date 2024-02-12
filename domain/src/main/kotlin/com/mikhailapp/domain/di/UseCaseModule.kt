package com.mikhailapp.domain.di

import com.mikhailapp.domain.usecases.ProductItemsUseCase
import com.mikhailapp.domain.usecases.ProductUseCase
import com.mikhailapp.domain.usecases.impl.ProductItemsUseCaseImpl
import com.mikhailapp.domain.usecases.impl.ProductUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindProductItemsUseCase(useCaseImpl: ProductItemsUseCaseImpl) : ProductItemsUseCase

    @Binds
    @Singleton
    abstract fun bindProductUseCase(useCaseImpl: ProductUseCaseImpl) : ProductUseCase
}