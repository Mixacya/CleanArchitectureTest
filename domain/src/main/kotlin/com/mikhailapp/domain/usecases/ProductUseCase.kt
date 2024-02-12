package com.mikhailapp.domain.usecases

import com.mikhailapp.domain.models.Details

interface ProductUseCase {
    suspend fun execute(itemId: String?): Details?
}