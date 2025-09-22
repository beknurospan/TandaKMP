package com.beknur.tanda.navigation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.beknur.tanda.feature.auth.AuthContent
import com.beknur.tanda.feature.cart.CartContent
import com.beknur.tanda.feature.catalog.CatalogContent
import com.beknur.tanda.feature.favorites.FavoritesContent
import com.beknur.tanda.feature.home.HomeContent
import com.beknur.tanda.feature.profile.ProfileContent


@Composable
fun RootContent(component: RootComponent) {
	Children(
		stack = component.stack
	) {
		when (val instance = it.instance) {
			is RootComponent.Child.Auth -> {
				AuthContent(component = instance.component)
			}

			is RootComponent.Child.Home -> {
				HomeContent(component = instance.component)
			}

			is RootComponent.Child.Cart -> {
				CartContent(component = instance.component)
			}
			is RootComponent.Child.Catalog -> {
				CatalogContent(component = instance.component)
			}
			is RootComponent.Child.Favorites -> {
				FavoritesContent(component = instance.component)
			}
			is RootComponent.Child.Profile -> {
				ProfileContent(component = instance.component)
			}
		}
	}

}




