package com.beknur.shared.domain.model

data class CartProduct(
	val productId:Int,
	val skuId: Int,
	val isSelected: Boolean,
	val detailText: String,
	val brandName: String,
	val size: String,
	val pricePerOne: Int,
	val count: Int=1,
){
	val sum:Int
		get() = pricePerOne * count
}