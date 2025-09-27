package com.beknur.shared.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.beknur.shared.datastore.UserDataSource
import okio.Path.Companion.toPath
import org.koin.dsl.module

val dataStoreModule = module {
	single<UserDataSource>{get()}
}

fun createDataStore(producePath: () -> String): DataStore<Preferences> =
	PreferenceDataStoreFactory.createWithPath(
		produceFile = { producePath().toPath() }
	)

const val dataStoreFileName = "app.preferences_pb"