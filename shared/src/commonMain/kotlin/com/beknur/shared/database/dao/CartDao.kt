package com.beknur.shared.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.beknur.shared.database.model.CartEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {
	@Query("SELECT * FROM cart")
	fun getCartEntities(): Flow<List<CartEntity>>

	@Insert
	suspend fun insertCartEntity(cartEntity: CartEntity)

	@Query("UPDATE cart SET count = count + 1 WHERE productId = :productId AND skuId= :skuId")
	suspend fun incrementItem(productId: Int,skuId: Int)

	@Query("UPDATE cart SET count = count - 1 WHERE productId = :productId AND skuId= :skuId")
	suspend fun decrementItem(productId: Int,skuId: Int)

	@Query("UPDATE cart SET isSelected = NOT isSelected WHERE productId = :productId AND skuId= :skuId")
	suspend fun toggleSelect(productId: Int,skuId: Int)

	@Query("UPDATE cart SET isSelected = 1")
	suspend fun selectAll()

	@Query("DELETE FROM cart WHERE isSelected = 1")
	suspend fun deleteSelected()
	@Query("SELECT * FROM cart WHERE productId = :productId AND skuId= :skuId LIMIT 1")
	suspend fun getCartItem(productId: Int,skuId: Int): CartEntity?

	@Transaction
	suspend fun addOrIncrement(cartEntity: CartEntity) {
		val existing = getCartItem(cartEntity.productId,cartEntity.skuId)
		if (existing != null) {
			incrementItem(cartEntity.productId, cartEntity.skuId)
		} else {
			insertCartEntity(cartEntity)
		}
	}
	@Query("SELECT * FROM cart WHERE isSelected = 1")
	suspend fun getSelectedCartItems(): List<CartEntity>

}