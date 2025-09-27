package com.beknur.shared.data.repository

import com.beknur.shared.data.mappers.toFilterParams
import com.beknur.shared.data.mappers.toProduct
import com.beknur.shared.data.mappers.toProductCategory
import com.beknur.shared.data.mappers.toProductDetail
import com.beknur.shared.domain.model.FilterParams
import com.beknur.shared.domain.model.Product
import com.beknur.shared.domain.model.ProductCategory
import com.beknur.shared.domain.model.ProductDetail
import com.beknur.shared.domain.repository.ProductRepository
import com.beknur.shared.domain.util.Resource
import com.beknur.shared.network.api.ProductApi


class ProductRepositoryImpl(
	private val productApi: ProductApi
): ProductRepository {


	override suspend fun getProductsByCategory(productCategory: String): List<ProductCategory> {
		return productApi.getProductsByCategory(productCategory).map { it.toProductCategory() }
	}

	override suspend fun getFiltersForCategory(filterCategory: String): Resource<FilterParams> {
		return try {
			val filterParams = productApi.getFiltersForCategory(filterCategory).toFilterParams()
			Resource.Success(filterParams)
		} catch (e: Exception) {
			Resource.Error(e)
		}
	}

	override suspend fun getProductDetail(id: Int, skuId: Int): Resource<ProductDetail> {
		return try {
			val productDetail = productApi.getProductDetail(id,skuId).toProductDetail()
			Resource.Success(productDetail)
		} catch (e: Exception) {
			Resource.Error(e)
		}
	}

	override suspend fun getProduct(id: Int, skuId: Int): Resource<Product> {
		return try {
			val resource=productApi.getProduct(id, skuId).toProduct()
			Resource.Success(resource)
		} catch (e: Exception) {
			Resource.Error(e)
		}
	}
}