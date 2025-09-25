package com.beknur.shared.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Address(
	val id: Int = -1,
	val address: String,
	val apartment: String,
	val entrance: String,
	val floor: String,
)