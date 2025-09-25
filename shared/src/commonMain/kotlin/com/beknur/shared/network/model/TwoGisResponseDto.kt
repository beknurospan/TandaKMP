package com.beknur.shared.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class TwoGisResponseDto(
	@SerialName("result")
	val result: TwoGisResultDto? = null
)

@Serializable
data class TwoGisResultDto(
	@SerialName("items")
	val items: List<MySearchResultDto>?=null
)

@Serializable
data class MySearchResultDto(
	@SerialName("full_name")
	val fullName: String? = null,

	@SerialName("address_name")
	val addressName: String? = null
)