package com.beknur.shared.domain.repository

import com.beknur.shared.domain.model.CartProduct
import com.beknur.shared.domain.model.Product
import com.beknur.shared.domain.model.ProductType
import com.beknur.shared.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CartRepository {
	fun getCartItems(): Flow<List<CartProduct>>
	suspend fun decrementItem(productId: Int,skuId: Int)
	suspend fun toggleSelect(productId: Int,skuId: Int)
	suspend fun selectAll()
	suspend fun deleteSelected()
	suspend fun getProductType(id:Int): ProductType
	suspend fun getProduct(id:Int,skuId: Int): Resource<CartProduct>
	suspend fun insertOrAdd(product: Product)
	suspend fun getTotalCartPrice(): Resource<Double>
}