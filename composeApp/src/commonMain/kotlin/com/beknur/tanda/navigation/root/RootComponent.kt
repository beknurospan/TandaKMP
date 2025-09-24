package com.beknur.tanda.navigation.root

import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.beknur.tanda.feature.auth.AuthComponent
import com.beknur.tanda.feature.cart.CartComponent
import com.beknur.tanda.feature.catalog.CatalogComponent
import com.beknur.tanda.feature.favorites.FavoritesComponent
import com.beknur.tanda.feature.home.HomeComponent
import com.beknur.tanda.feature.profile.ProfileComponent
import com.beknur.tanda.navigation.AppTab
import kotlinx.coroutines.flow.StateFlow

interface RootComponent {

	val stack: Value<ChildStack<*, Child>>

	val model: StateFlow<RootStore.State>

	sealed interface Child {

		data class Auth(val component: AuthComponent) : Child

		data class Home(val component: HomeComponent) : Child

		data class Catalog(val component: CatalogComponent) : Child

		data class Cart(val component: CartComponent) : Child

		data class Favorites(val component: FavoritesComponent) : Child

		data class Profile(val component: ProfileComponent) : Child
	}

	fun onClickTab(bottomNavItem: AppTab)

}