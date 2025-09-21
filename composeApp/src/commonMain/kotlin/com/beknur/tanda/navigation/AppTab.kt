package com.beknur.tanda.navigation

sealed interface AppTab {
	data object Home : AppTab
	data object Catalog : AppTab
	data object Cart : AppTab
	data object Favorites : AppTab
	data object Profile : AppTab
}

