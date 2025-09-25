package com.beknur.shared.domain.model



data class ProductType(
	val variants: List<ProductVariants>
)
data class ProductVariants(
	val skuId: Int,
	val	size: Int,
)