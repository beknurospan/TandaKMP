package com.beknur.tanda.navigation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.beknur.tanda.extensions.componentScope
import com.beknur.tanda.feature.auth.DefaultAuthComponent
import com.beknur.tanda.feature.cart.DefaultCartComponent
import com.beknur.tanda.feature.catalog.DefaultCatalogComponent
import com.beknur.tanda.feature.favorites.DefaultFavoritesComponent
import com.beknur.tanda.feature.home.DefaultHomeComponent
import com.beknur.tanda.feature.profile.DefaultProfileComponent
import com.beknur.tanda.navigation.AppTab
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.core.KoinApplication.Companion.init


@OptIn(DelicateDecomposeApi::class, ExperimentalCoroutinesApi::class)
class DefaultRootComponent(
	context: ComponentContext,
	private val authComponentFactory: DefaultAuthComponent.Factory,
	private val homeComponentFactory: DefaultHomeComponent.Factory,
	private val cartComponentFactory: DefaultCartComponent.Factory,
	private val profileComponentFactory: DefaultProfileComponent.Factory,
	private val catalogComponentFactory: DefaultCatalogComponent.Factory,
	private val favoritesComponentFactory: DefaultFavoritesComponent.Factory,
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
		initialConfiguration = Config.Favorites,
		handleBackButton = true,
		childFactory = ::child
	)

	init {
		scope.launch {


			store.stateFlow.collect { state ->
				if (!state.isAuthChecked) {
					navigation.replaceAll(Config.Favorites)

				} else if (state.isAuthorized) {
					navigation.replaceAll(Config.Catalog)
				} else {
					navigation.replaceAll(Config.Auth)
				}
			}
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
				RootComponent.Child.Auth(component)
			}
			Config.Home -> {
				val component = homeComponentFactory.create(componentContext)
				RootComponent.Child.Home(component)
			}

			Config.Cart -> {
				val component = cartComponentFactory.create(componentContext)
				RootComponent.Child.Cart(component)
			}
			Config.Catalog -> {
				val component = catalogComponentFactory.create(componentContext)
				RootComponent.Child.Catalog(component)
			}
			Config.Favorites -> {
				val component = favoritesComponentFactory.create(componentContext)
				RootComponent.Child.Favorites(component)
			}
			Config.Profile -> {
				val component = profileComponentFactory.create(componentContext)
				RootComponent.Child.Profile(component)
			}
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
		private val cartComponentFactory: DefaultCartComponent.Factory,
		private val profileComponentFactory: DefaultProfileComponent.Factory,
		private val catalogComponentFactory: DefaultCatalogComponent.Factory,
		private val favoritesComponentFactory: DefaultFavoritesComponent.Factory


	){
		fun create(
			componentContext: ComponentContext
		)=DefaultRootComponent(
				context = componentContext,
				authComponentFactory = authComponentFactory,
				rootStoreFactory = rootStoreFactory,
				homeComponentFactory = homeComponentFactory,
				cartComponentFactory = cartComponentFactory,
				profileComponentFactory = profileComponentFactory,
				catalogComponentFactory = catalogComponentFactory,
				favoritesComponentFactory = favoritesComponentFactory
			)

	}

	private fun AppTab.toConfig(): Config = when(this){
		AppTab.Home -> Config.Home
		AppTab.Cart -> Config.Cart
		AppTab.Catalog -> Config.Catalog
		AppTab.Favorites -> Config.Favorites
		AppTab.Profile -> Config.Profile
	}

}