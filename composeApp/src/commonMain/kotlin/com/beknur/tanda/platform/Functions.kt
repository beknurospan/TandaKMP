@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.beknur.tanda.platform

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import com.beknur.shared.database.TndDatabase


expect fun getRoomDatabaseBuilder(): RoomDatabase.Builder<TndDatabase>
expect fun getDataStorePreferences(): DataStore<Preferences>


