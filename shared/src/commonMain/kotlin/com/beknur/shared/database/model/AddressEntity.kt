package com.beknur.shared.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "address")
data class AddressEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int=0,
	val address: String,
	val apartment: String,
	val entrance: String,
	val floor: String,
	val isChosen: Boolean=true
)