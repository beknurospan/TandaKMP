package com.beknur.tanda.navigation

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import tandakmp.composeapp.generated.resources.Res
import tandakmp.composeapp.generated.resources.cart
import tandakmp.composeapp.generated.resources.catalog
import tandakmp.composeapp.generated.resources.favorites
import tandakmp.composeapp.generated.resources.home
import tandakmp.composeapp.generated.resources.profile
import tandakmp.composeapp.generated.resources.s1
import tandakmp.composeapp.generated.resources.s2
import tandakmp.composeapp.generated.resources.s3
import tandakmp.composeapp.generated.resources.shopicons_light_cart3
import tandakmp.composeapp.generated.resources.shopicons_light_store


data class BottomBarItem(
	val title: StringResource,
	val appTab: AppTab,
	val icon: DrawableResource
)  {
	companion object {
		fun getItems() = listOf(
			BottomBarItem(Res.string.home, AppTab.Home, Res.drawable.s1),
			BottomBarItem(Res.string.catalog, AppTab.Catalog, Res.drawable.shopicons_light_store),
			BottomBarItem(Res.string.cart, AppTab.Cart, Res.drawable.shopicons_light_cart3),
			BottomBarItem(Res.string.favorites, AppTab.Favorites, Res.drawable.s2),
			BottomBarItem(Res.string.profile, AppTab.Profile, Res.drawable.s3),
		)
	}
}
