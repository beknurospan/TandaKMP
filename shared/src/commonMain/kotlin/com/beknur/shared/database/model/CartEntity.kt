package com.beknur.shared.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
	tableName = "cart",
	primaryKeys = ["productId", "skuId"]
)
data class CartEntity(
	val productId:Int,
	val skuId: Int,
	val isSelected: Boolean,
	val detailText: String,
	val brandName: String,
	val size: String,
	val pricePerOne: Int,
	val count: Int
)