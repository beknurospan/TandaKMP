package com.beknur.shared.database.model

import androidx.room.Entity


@Entity(
	tableName = "favorite",
	primaryKeys = ["productId"]
)
data class FavoriteEntity(
	val productId:Int,
	val name: String,
	val brandName: String,
	val sizes: List<Int>,
	val price: Double,
	val img: String
)

