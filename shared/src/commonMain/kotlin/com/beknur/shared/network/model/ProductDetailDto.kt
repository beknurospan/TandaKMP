package com.beknur.shared.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProductDetailDto(
	@SerialName("product_id")
	val productId: Int,
	@SerialName("price")
	val price: Double,
	@SerialName("sizes")
	val sizes: List<SizeItemDto>,
	@SerialName("rating")
	val rating: Double,
	@SerialName("name")
	val name: String,
	@SerialName("brand_name")
	val brandName: String,
	@SerialName("images")
	val imageList:List<String>,
	@SerialName("is_favorite")
	val isFavorite: Boolean,
	@SerialName("other_colors")
	val otherColors:List<OtherColorDto> =emptyList<OtherColorDto>(),
	@SerialName("characteristics")
	val characteristics: List<CharacteristicDto> = emptyList<CharacteristicDto>()
)

@Serializable
data class OtherColorDto(
	@SerialName("color_name")
	val colorName: String,
	@SerialName("image_url")
	val imageUrl: String
)

@Serializable
data class CharacteristicDto(
	@SerialName("id")
	val characteristicId: Int,
	@SerialName("name")
	val characteristicName: String,
	@SerialName("definition")
	val characteristicValue: String
)


@Serializable
data class SizeItemDto(
	@SerialName("size")
	val size: Int,
	@SerialName("is_available")
	val isAvailable: Boolean,
	@SerialName("count")
	val count: Int = 0,
	@SerialName("sku_id")
	val skuId: Int
)