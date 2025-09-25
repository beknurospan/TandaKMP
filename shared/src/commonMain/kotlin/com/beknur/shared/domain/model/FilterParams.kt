package com.beknur.shared.domain.model


data class FilterParams(
	val category:String,
	val sizeAttribute: FilterAttribute,
	val brandAttribute: FilterAttribute,
	val priceRange: PriceRange,
	val filterAttributes:List<FilterAttribute>,
)


data class FilterAttribute(
	val id:Int,
	val nameAttribute: String,
	val params: Set<Params>
)

data class Params(
	val id:Int,
	val name:String
)

data class PriceRange(
	val min:Long,
	val max:Long
)