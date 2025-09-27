package com.beknur.shared.database.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.beknur.shared.database.TndDatabase
import com.beknur.shared.database.dao.AddressDao
import com.beknur.shared.database.dao.CartDao
import com.beknur.shared.database.dao.FavoriteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val databaseModule = module {

	single<TndDatabase> { getMovieDatabase(get()) }
	single<CartDao> { get<TndDatabase>().cartDao() }
	single<AddressDao> { get<TndDatabase>().addressDao() }
	single<FavoriteDao> { get<TndDatabase>().favoriteDao() }
}


fun getMovieDatabase(builder: RoomDatabase.Builder<TndDatabase>): TndDatabase {
	return builder
		.setDriver(BundledSQLiteDriver())
		.setQueryCoroutineContext(Dispatchers.IO)
		.build()
}