package com.beknur.shared.domain.repository

import com.beknur.shared.domain.model.FilterParams
import com.beknur.shared.domain.model.Product
import com.beknur.shared.domain.model.ProductCategory
import com.beknur.shared.domain.model.ProductDetail
import com.beknur.shared.domain.util.Resource

interface ProductRepository {
	suspend fun getProductsByCategory(productCategory: String): List<ProductCategory>
	suspend fun getFiltersForCategory(filterCategory: String): Resource<FilterParams>
	suspend fun getProductDetail(id:Int,skuId:Int): Resource<ProductDetail>
	suspend fun getProduct(id:Int,skuId:Int): Resource<Product>
}

