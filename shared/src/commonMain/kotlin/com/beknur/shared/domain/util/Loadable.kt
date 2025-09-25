package com.beknur.shared.domain.util

sealed class Loadable<out T> {
	object Idle : Loadable<Nothing>()
	object Loading : Loadable<Nothing>()
	data class Success<T>(val data: T) : Loadable<T>()
	data class Error(val message: Throwable) : Loadable<Nothing>()
}