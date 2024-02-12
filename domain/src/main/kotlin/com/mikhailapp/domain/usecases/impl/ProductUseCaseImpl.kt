package com.mikhailapp.domain.usecases.impl

import com.mikhailapp.data.repository.NetworkRepository
import com.mikhailapp.domain.models.Details
import com.mikhailapp.data.models.Details as DataDetails
import com.mikhailapp.domain.usecases.ProductUseCase
import javax.inject.Inject

class ProductUseCaseImpl @Inject constructor(
    private val repository: NetworkRepository
) : ProductUseCase {

    override suspend fun execute(itemId: String?): Details? {
        if (itemId != null) {
            val product = repository.getProductById(itemId)
            return convertProduct(product)
        }
        return null
    }

    private fun convertProduct(productData: DataDetails): Details {
        return Details(
            id = productData.id,
            text = productData.text
        )
    }

}