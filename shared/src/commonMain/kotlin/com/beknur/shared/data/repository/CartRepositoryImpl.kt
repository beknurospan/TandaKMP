package com.beknur.shared.data.repository





import com.beknur.shared.data.mappers.toCartEntity
import com.beknur.shared.data.mappers.toCartProduct
import com.beknur.shared.data.mappers.toProductType
import com.beknur.shared.database.dao.CartDao
import com.beknur.shared.domain.model.CartProduct
import com.beknur.shared.domain.model.Product

import com.beknur.shared.domain.model.ProductType
import com.beknur.shared.domain.repository.CartRepository
import com.beknur.shared.domain.util.Resource
import com.beknur.shared.network.api.ProductApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class CartRepositoryImpl(
	private val cartDao: CartDao, private val productApi: ProductApi
) : CartRepository {


	override fun getCartItems(): Flow<List<CartProduct>> {

		return cartDao.getCartEntities().map { it.map { it.toCartProduct() } }

	}

	override suspend fun decrementItem(productId: Int, skuId: Int) {
		cartDao.decrementItem(productId = productId, skuId = skuId)
	}

	override suspend fun toggleSelect(productId: Int, skuId: Int) {
		cartDao.toggleSelect(productId = productId, skuId = skuId)
	}

	override suspend fun selectAll() {
		cartDao.selectAll()
	}

	override suspend fun deleteSelected() {
		cartDao.deleteSelected()
	}

	override suspend fun getProductType(id: Int): ProductType {
		return productApi.getProductType(id).toProductType()
	}

	override suspend fun getProduct(id: Int, skuId: Int): Resource<CartProduct> {
		return try {
			val resource=productApi.getProduct(id, skuId).toCartProduct()
			Resource.Success(resource)
		} catch (e: Exception) {
			Resource.Error(e)
		}
	}

	override suspend fun insertOrAdd(product: Product) {
		val cartProduct=product.toCartProduct()
		cartDao.addOrIncrement(cartProduct.toCartEntity())
	}

	override suspend fun getTotalCartPrice(): Resource<Double> {

		return try {
			var totalPrice=0
			val selectedCartItems=cartDao.getSelectedCartItems()
			selectedCartItems.forEach { cartProduct ->
				totalPrice=cartProduct.pricePerOne*cartProduct.count
			}
			Resource.Success(totalPrice.toDouble())
		} catch (e: Exception) {
			Resource.Error(Exception(e))
		}

	}

}