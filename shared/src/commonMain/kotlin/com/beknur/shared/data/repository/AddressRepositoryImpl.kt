package com.beknur.shared.data.repository

import com.beknur.shared.data.mappers.toAddress
import com.beknur.shared.data.mappers.toAddressEntity
import com.beknur.shared.database.dao.AddressDao
import com.beknur.shared.domain.model.Address
import com.beknur.shared.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AddressRepositoryImpl(private val addressDao: AddressDao): AddressRepository {
	override fun getAddresses(): Flow<List<Address>> {
		return addressDao.getAddressEntities().map { it.map { it.toAddress() } }
	}

	override suspend fun getAddressById(id: Int): Address {
		return addressDao.getAddressById(id).toAddress()
	}


	override suspend fun addAndSetChosen(address: Address) {
		addressDao.addAndSetChosen(address.toAddressEntity())
	}

	override suspend fun deleteAddress(id: Int) {
		addressDao.deleteAddress(id)
	}

	override suspend fun updateAddressById(
		id: Int,
		address:String,
		entrance: String,
		floor: String,
		apartment: String
	) {
		addressDao.updateAddressById(id,address,entrance,floor,apartment)
	}

	override suspend fun setChosenAndUnchooseAll(id: Int){
		addressDao.setChosenAndUnchooseAll(id)
	}
}