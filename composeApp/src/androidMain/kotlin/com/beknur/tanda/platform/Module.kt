package com.beknur.tanda.platform

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import com.beknur.shared.database.TndDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
	get() = module {
		single<RoomDatabase.Builder<TndDatabase>> {
			getRoomDatabaseBuilder()
		}
		single<HttpClientEngine> {
			CIO.create {

			}
		}
		single<DataStore<Preferences>> {
			getDataStorePreferences()
		}
	}

