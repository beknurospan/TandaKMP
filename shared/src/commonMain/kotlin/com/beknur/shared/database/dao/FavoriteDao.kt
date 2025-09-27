package com.beknur.shared.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.beknur.shared.database.model.FavoriteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {
	@Query("SELECT * FROM favorite")
	fun getFavoriteEntities(): Flow<List<FavoriteEntity>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertFavoriteEntity(favoriteEntity: FavoriteEntity)

	@Query("DELETE FROM favorite WHERE productId = :productId")
	suspend fun deleteFavoriteEntity(productId: Int)

	@Upsert
	suspend fun updateFavoriteEntity(favoriteEntity: FavoriteEntity)

}