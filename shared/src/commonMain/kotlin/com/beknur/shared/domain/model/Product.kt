package com.beknur.shared.domain.model

data class Product(
	val productId: Int,
	val skuId: Int,
	val price: Double,
	val size:Int,
	val rating: Double,
	val name: String,
	val brandName: String,
	val img: String
)

data class ProductCategory(
	val productId: Int,
	val skuId: Int,
	val price: Double,
	val sizes: List<Int>,
	val rating: Double,
	val name: String,
	val brandName: String,
	val img: String
)


