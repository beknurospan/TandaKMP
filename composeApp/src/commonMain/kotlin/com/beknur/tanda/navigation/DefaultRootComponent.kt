package com.beknur.tanda.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.beknur.tanda.extensions.componentScope
import com.beknur.tanda.feature.auth.DefaultAuthComponent
import com.beknur.tanda.feature.home.DefaultHomeComponent
import com.beknur.tanda.feature.home.HomeContent
import com.beknur.tanda.navigation.RootComponent.Child.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


@OptIn(DelicateDecomposeApi::class)
class DefaultRootComponent(
	context: ComponentContext,
	private val authComponentFactory: DefaultAuthComponent.Factory,
	private val homeComponentFactory: DefaultHomeComponent.Factory,
	private val rootStoreFactory: RootStoreFactory
) : RootComponent, ComponentContext by context {

	private val store = instanceKeeper.getStore { rootStoreFactory.create() }
	private val scope = componentScope()
	private val navigation = StackNavigation<Config>()

	@OptIn(ExperimentalCoroutinesApi::class)
	override val model: StateFlow<RootStore.State> = store.stateFlow


	override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
		source = navigation,
		serializer = Config.serializer(),
		initialConfiguration = Config.Auth,
		handleBackButton = true,
		childFactory = ::child
	)

	init {
		scope.launch {
			store.labels.collect{
				when(it){
					is RootStore.Label.ClickTab -> {
						val tabConfig=it.bottomNavItem.toConfig()
						navigation.bringToFront(tabConfig)
					}
				}
			}
		}
	}
	override fun onClickTab(bottomNavItem: AppTab) {
		store.accept(RootStore.Intent.OnClickTab(bottomNavItem))
	}

	private fun child(
		config: Config, componentContext: ComponentContext
	): RootComponent.Child {
		return when (config) {
			Config.Auth -> {
				val component = authComponentFactory.create(componentContext)
				Auth(component)
			}
			Config.Home -> {
				val component = homeComponentFactory.create(componentContext)
				Home(component)
			}

			Config.Cart -> TODO()
			Config.Catalog -> TODO()
			Config.Favorites -> TODO()
			Config.Profile -> TODO()
		}
	}


	@Serializable
	sealed interface Config  {
		@Serializable
		data object Auth : Config

		@Serializable
		data object Home: Config

		@Serializable
		data object Catalog : Config

		@Serializable
		data object Cart : Config

		@Serializable
		data object Favorites : Config

		@Serializable
		data object Profile : Config

	}

	class Factory (
		private val authComponentFactory: DefaultAuthComponent.Factory,
		private val homeComponentFactory: DefaultHomeComponent.Factory,
		private val rootStoreFactory: RootStoreFactory,


	){
		fun create(
			componentContext: ComponentContext
		)=DefaultRootComponent(
				context = componentContext,
				authComponentFactory = authComponentFactory,
				rootStoreFactory = rootStoreFactory,
				homeComponentFactory = homeComponentFactory
			)

	}

	private fun AppTab.toConfig(): DefaultRootComponent.Config = when(this){
		AppTab.Home -> DefaultRootComponent.Config.Home
		AppTab.Cart -> DefaultRootComponent.Config.Cart
		AppTab.Catalog -> DefaultRootComponent.Config.Catalog
		AppTab.Favorites -> DefaultRootComponent.Config.Favorites
		AppTab.Profile -> DefaultRootComponent.Config.Profile
	}

}