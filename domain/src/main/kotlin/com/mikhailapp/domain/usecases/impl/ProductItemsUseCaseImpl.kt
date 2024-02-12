package com.mikhailapp.domain.usecases.impl

import com.mikhailapp.data.repository.NetworkRepository
import com.mikhailapp.domain.models.Product
import com.mikhailapp.data.models.Product as DataProduct
import com.mikhailapp.domain.models.ProductItems
import com.mikhailapp.data.models.ProductItems as DataProductItems
import com.mikhailapp.domain.usecases.ProductItemsUseCase
import javax.inject.Inject

class ProductItemsUseCaseImpl @Inject constructor(
    private val repository: NetworkRepository
) : ProductItemsUseCase {
    override suspend fun execute(): ProductItems {
        return convertProductItems(repository.getRandomItems())
    }

    private fun convertProductItems(productItems: DataProductItems) = ProductItems(
        title = productItems.title,
        products = convertItems(productItems.products)
    )

    private fun convertItems(dataItems: List<DataProduct>?): MutableList<Product> {
        val dataList = mutableListOf<Product>()
        if (dataItems != null) {
            for (dataItem: DataProduct in dataItems) {
                val product = Product(
                    id = dataItem.id,
                    name = dataItem.name,
                    imageUrl = dataItem.imageUrl,
                    color = dataItem.color
                )
                dataList.add(product)
            }
        }
        return dataList
    }
}