package com.beknur.shared.database.converter

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class Converters {
	private val json = Json {
		ignoreUnknownKeys = true
		encodeDefaults = true
	}

	@TypeConverter
	fun fromList(value: List<Int>): String =
		json.encodeToString(value)

	@TypeConverter
	fun toList(value: String): List<Int> =
		json.decodeFromString(value)
}