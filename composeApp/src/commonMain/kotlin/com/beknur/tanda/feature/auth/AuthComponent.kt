package com.beknur.tanda.feature.auth

import com.beknur.tanda.navigation.AppTab

interface AuthComponent {
	fun onLoginClick(email: String, password: String)
}