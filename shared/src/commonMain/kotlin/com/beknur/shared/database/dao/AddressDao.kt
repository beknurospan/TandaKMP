	package com.beknur.shared.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.beknur.shared.database.model.AddressEntity
import kotlinx.coroutines.flow.Flow


	@Dao
interface AddressDao {
	@Query("SELECT * FROM address  ORDER BY isChosen DESC")
	fun getAddressEntities(): Flow<List<AddressEntity>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAddressEntity(addressEntity: AddressEntity)

	@Query("UPDATE address SET isChosen = 0")
	suspend fun unchooseAll()

	@Transaction
	suspend fun addAndSetChosen(addressEntity: AddressEntity) {
		unchooseAll()
		insertAddressEntity(addressEntity)
	}

	@Transaction
	suspend fun setChosenAndUnchooseAll(id: Int) {
		unchooseAll()
		chooseAddress(id)
	}
	@Query("UPDATE address SET isChosen = 1 WHERE id = :id")
	suspend fun chooseAddress(id: Int)

	@Query("UPDATE address SET address = :address, entrance = :entrance, floor = :floor, apartment = :apartment WHERE id = :id")
	suspend fun updateAddressById(id: Int,address: String,entrance: String,floor: String,apartment: String)

	@Query("DELETE FROM address WHERE id = :id")
	suspend fun deleteAddress(id: Int)

	@Query("SELECT * FROM address WHERE id = :id")
	suspend fun getAddressById(id: Int): AddressEntity


}