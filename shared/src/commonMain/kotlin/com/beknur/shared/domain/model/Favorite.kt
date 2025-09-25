package com.beknur.shared.domain.model

data class Favorite(
	val productId:Int,
	val name: String,
	val brandName: String,
	val sizes: List<Int>,
	val price: Double,
	val img: String
)