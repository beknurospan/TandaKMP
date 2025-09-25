package com.beknur.shared.domain.repository

import com.beknur.shared.domain.model.Address
import com.beknur.shared.domain.model.MySearchResult
import com.beknur.shared.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
	suspend fun getSearchResult(query: String):List<MySearchResult>
	suspend fun getUserData():Flow<UserData>
	suspend fun updateUserName(userName: String)
	suspend fun updateSuitableTimeDelivery(suitableTimeDelivery: String)
	suspend fun updateChosenAddress(chosenAddress: Address)
	suspend fun updateAdditionalAddressInfo(additionalAddressInfo: String)
}