package com.beknur.shared.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserData(
	@SerialName("user_name")
	val userName: String = "",
	@SerialName("user_phone")
	val userPhone: String = "",
	@SerialName("user_birth")
	val userBirth: String = "",
	@SerialName("suitable_time_delivery")
	val suitableTimeDelivery: String = "",
	@SerialName("chosen_address")
	val chosenAddress: Address = Address(address = "", apartment = "", entrance =  "", floor = ""),
	@SerialName("additional_address_info")
	val additionalAddressInfo: String = "",
)


