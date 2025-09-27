@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.beknur.shared.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.beknur.shared.database.converter.Converters
import com.beknur.shared.database.dao.AddressDao
import com.beknur.shared.database.dao.CartDao
import com.beknur.shared.database.dao.FavoriteDao
import com.beknur.shared.database.model.AddressEntity
import com.beknur.shared.database.model.CartEntity
import com.beknur.shared.database.model.FavoriteEntity


@Database(
	entities = [CartEntity::class, AddressEntity::class, FavoriteEntity::class],
	version =1 ,
	exportSchema = true
)
@ConstructedBy(AppDatabaseConstructor::class)
@TypeConverters(Converters::class)
abstract class TndDatabase : RoomDatabase() {
	abstract fun cartDao(): CartDao
	abstract fun addressDao(): AddressDao
	abstract fun favoriteDao(): FavoriteDao
}


@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<TndDatabase> {
	override fun initialize(): TndDatabase
}