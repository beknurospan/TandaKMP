package com.beknur.shared.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.beknur.shared.domain.model.Address
import com.beknur.shared.domain.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class UserDataSource(private val dataStore: DataStore<Preferences>) {


	private val userNameKey = stringPreferencesKey("user_name")
	private val userBirthKey = stringPreferencesKey("user_birth")
	private val userPhoneKey = stringPreferencesKey("user_phone")
	private val chosenAddressKey = stringPreferencesKey("chosen_address")
	private val additionalAddressInfoKey = stringPreferencesKey("additional_address_info")
	private val suitableTimeDeliveryKey = stringPreferencesKey("suitable_time_delivery")


	val userName: Flow<String?> = dataStore.data.map { it[userNameKey] }
	val userBirth: Flow<String?> = dataStore.data.map { it[userBirthKey] }
	val userPhone: Flow<String?> = dataStore.data.map { it[userPhoneKey] }
	val chosenAddress: Flow<String?> = dataStore.data.map { it[chosenAddressKey] }
	val additionalAddressInfo: Flow<String?> = dataStore.data.map { it[additionalAddressInfoKey] }
	val suitableTimeDelivery: Flow<String?> = dataStore.data.map { it[suitableTimeDeliveryKey] }

	fun getUserData(): Flow<UserData> = dataStore.data.map { prefs ->
		UserData(
			userName = prefs[userNameKey] ?: "",
			userBirth = prefs[userBirthKey] ?: "",
			userPhone = prefs[userPhoneKey] ?: "",
			chosenAddress = prefs[chosenAddressKey]?.let { Json.decodeFromString<Address>(it) } ?:
			Address(
				address = "",
				apartment = "",
				entrance = "",
				floor = ""
			),
			additionalAddressInfo = prefs[additionalAddressInfoKey] ?: "",
			suitableTimeDelivery = prefs[suitableTimeDeliveryKey] ?: ""
		)
	}
	suspend fun updateUserName(name: String) {
		dataStore.edit { it[userNameKey] = name }
	}

	suspend fun updateUserBirth(birth: String) {
		dataStore.edit { it[userBirthKey] = birth }
	}

	suspend fun updateUserPhone(phone: String) {
		dataStore.edit { it[userPhoneKey] = phone }
	}

	suspend fun updateChosenAddress(addressJson: Address) {
		val addressJson = Json.encodeToString(Address.serializer(), addressJson)
		dataStore.edit { it[chosenAddressKey] = addressJson }
	}

	suspend fun updateAdditionalAddress(info: String) {
		dataStore.edit { it[additionalAddressInfoKey] = info }
	}

	suspend fun updateSuitableTimeDelivery(time: String) {
		dataStore.edit { it[suitableTimeDeliveryKey] = time }
	}
}