package com.beknur.tanda.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.beknur.tanda.feature.auth.AuthComponent
import com.beknur.tanda.feature.home.HomeComponent
import kotlinx.coroutines.flow.StateFlow

interface RootComponent {

	val stack: Value<ChildStack<*, Child>>

	val model: StateFlow<RootStore.State>

	sealed interface Child {

		data class Auth(val component: AuthComponent) : Child

		data class Home(val component: HomeComponent) : Child
	}

	fun onClickTab(bottomNavItem: AppTab)
}