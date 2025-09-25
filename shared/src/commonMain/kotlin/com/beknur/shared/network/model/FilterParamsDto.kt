package com.beknur.shared.network.model

import kotlinx.serialization.Serializable

@Serializable
data class FilterParamsDto(
	val category: String? = null,
	val sizeAttribute: FilterAttributeDto? = null,
	val brandAttribute: FilterAttributeDto? = null,
	val priceRangeDto: PriceRangeDto? = null,
	val filterAttributes: List<FilterAttributeDto>? = null,
)

@Serializable
data class FilterAttributeDto(
	val id: Int,
	val name: String,
	val parameters: List<ParamDto>,
)

@Serializable
data class ParamDto(
	val id: Int,
	val name: String
)

@Serializable
data class PriceRangeDto(
	val min: Long,
	val max: Long
)