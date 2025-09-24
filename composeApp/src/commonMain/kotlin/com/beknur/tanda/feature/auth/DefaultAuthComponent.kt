package com.beknur.tanda.feature.auth

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.beknur.tanda.extensions.componentScope
import com.beknur.tanda.navigation.root.DefaultRootComponent.Config
import com.beknur.tanda.navigation.root.RootStore
import kotlinx.coroutines.launch

class DefaultAuthComponent(
	context: ComponentContext,
	private val authStoreFactory: AuthStoreFactory,
	private val onLoginSuccess: () -> Unit,
) : AuthComponent, ComponentContext by context {

	private val store = instanceKeeper.getStore {authStoreFactory.create()}
	private val scope= componentScope()

	override fun onLoginClick(email: String, password: String) {
		store.accept(
			AuthStore.Intent.OnLoginClick(email, password)
		)
	}

	init {
		scope.launch {

			store.labels.collect{
				when(it){
					AuthStore.Label.LoginSuccess -> {
						onLoginSuccess()
					}
				}
			}
		}
	}


	class Factory(
		private val authStoreFactory: AuthStoreFactory,
	) {
		fun create(
			context: ComponentContext,
			onLoginSuccess: () -> Unit
		) = DefaultAuthComponent(
			context,
			authStoreFactory = authStoreFactory,
			onLoginSuccess = onLoginSuccess
		)
	}
}