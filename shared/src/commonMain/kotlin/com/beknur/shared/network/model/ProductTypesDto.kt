package com.beknur.shared.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductTypesDto(
	@SerialName("product_variants")
	val variants: List<ProductVariantDto>
)


@Serializable
data class ProductVariantDto(
	@SerialName("sku_id")
	val skuId: Int,
	@SerialName("size")
	val size: Int,
)



@Serializable
data class ProductDto(
	@SerialName("product_id")
	val productId: Int,

	@SerialName("sku_id")
	val skuId: Int,

	@SerialName("brand_name")
	val brandName: String,

	@SerialName("name")
	val name: String,

	@SerialName("size")
	val size: Int,

	@SerialName("image")
	val image: String,

	@SerialName("price_per_one")
	val pricePerOne: Double,

	@SerialName("rating")
	val rating: Double
)

@Serializable
data class ProductCategoryDto(
	val productId:Int,
	val skuId: Int,
	val price: Double,
	val sizes:List<Int>,
	val rating: Double,
	val name:String,
	val img:String,
	val brandName: String
)