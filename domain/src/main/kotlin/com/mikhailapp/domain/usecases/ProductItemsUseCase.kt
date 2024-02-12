package com.mikhailapp.domain.usecases

import com.mikhailapp.domain.models.ProductItems

interface ProductItemsUseCase {
    suspend fun execute() : ProductItems
}