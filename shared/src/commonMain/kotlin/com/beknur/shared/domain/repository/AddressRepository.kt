package com.beknur.shared.domain.repository

import com.beknur.shared.domain.model.Address
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
	fun getAddresses(): Flow<List<Address>>
	suspend fun getAddressById(id: Int): Address
	suspend fun addAndSetChosen(addressEntity: Address)
	suspend fun deleteAddress(id: Int)
	suspend fun updateAddressById(id: Int, address: String, entrance: String, floor: String, apartment: String)
	suspend fun setChosenAndUnchooseAll(id:Int)
}