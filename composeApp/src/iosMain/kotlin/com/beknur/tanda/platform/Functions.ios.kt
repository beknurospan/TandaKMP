@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.beknur.tanda.platform

import androidx.room.Room
import androidx.room.RoomDatabase
import com.beknur.shared.database.TndDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask


actual fun getRoomDatabaseBuilder(): RoomDatabase.Builder<TndDatabase> {
	val dbFilePath = documentDirectory() + "/tanda_database.db"
	return Room.databaseBuilder<TndDatabase>(
		name = dbFilePath,
	)
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
	val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
		directory = NSDocumentDirectory,
		inDomain = NSUserDomainMask,
		appropriateForURL = null,
		create = false,
		error = null,
	)

	return requireNotNull(documentDirectory?.path)
}