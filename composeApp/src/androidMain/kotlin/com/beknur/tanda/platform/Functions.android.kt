@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.beknur.tanda.platform

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.beknur.shared.database.TndDatabase
import com.beknur.shared.datastore.di.createDataStore
import com.beknur.shared.datastore.di.dataStoreFileName
import com.beknur.tanda.TandaApplication


actual fun getRoomDatabaseBuilder(): RoomDatabase.Builder<TndDatabase> {
	val appContext = TandaApplication.appContext
	val dbFile = appContext.getDatabasePath("tanda_database.db")

	return Room.databaseBuilder<TndDatabase>(
		context = appContext,
		name = dbFile.absolutePath,
	)
}

actual fun getDataStorePreferences(): DataStore<Preferences> {
	val appContext = TandaApplication.appContext
	return createDataStore(context = appContext)
}

fun createDataStore(context: Context): DataStore<Preferences> = createDataStore(
	producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
)