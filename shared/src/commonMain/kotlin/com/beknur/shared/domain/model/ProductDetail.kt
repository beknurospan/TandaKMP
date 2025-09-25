package com.beknur.shared.domain.model



data class ProductDetail(
	val productId: Int,
	val price: Double,
	val sizes: List<SizeItem>,
	val rating: Double,
	val name: String,
	val brandName: String,
	val imageList: List<String>,
	val isFavorite: Boolean,
	val otherColors: List<OtherColor> =emptyList<OtherColor>(),
	val characteristics: List<Characteristic>
)

data class SizeItem(
	val size: Int,
	val isAvailable: Boolean,
	val count: Int = 0,
	val skuId:Int
)