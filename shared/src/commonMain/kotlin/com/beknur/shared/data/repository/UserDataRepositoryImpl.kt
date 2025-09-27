package com.beknur.shared.data.repository

import com.beknur.shared.data.mappers.toMySearchResult
import com.beknur.shared.datastore.UserDataSource
import com.beknur.shared.domain.model.Address
import com.beknur.shared.domain.model.MySearchResult
import com.beknur.shared.domain.model.UserData
import com.beknur.shared.domain.repository.UserDataRepository
import com.beknur.shared.network.api.DgisApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow

class UserDataRepositoryImpl(
	private val twoGisApi: DgisApi,
	private val userData: UserDataSource
): UserDataRepository {
	@OptIn(FlowPreview::class)
	override suspend fun getSearchResult(query: String): List<MySearchResult> {
		val list= twoGisApi.fetchSuggestions(query)
		flow{
			emit(list)
		}.debounce {
			1000L
		}
		return list.filter { it.addressName!=null }.map { it.toMySearchResult()}

	}
	override suspend fun getUserData(): Flow<UserData> {
		return userData.getUserData()
	}

	override suspend fun updateUserName(userName: String) {
		userData.updateUserName(userName)
	}
	override suspend fun updateSuitableTimeDelivery(suitableTimeDelivery: String) {
		userData.updateSuitableTimeDelivery(suitableTimeDelivery)
	}

	override suspend fun updateChosenAddress(chosenAddress: Address) {
		userData.updateChosenAddress(chosenAddress)
	}

	override suspend fun updateAdditionalAddressInfo(additionalAddressInfo: String) {
		userData.updateAdditionalAddress(additionalAddressInfo)
	}
}

