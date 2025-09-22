package com.beknur.tanda.feature.favorites

import com.arkivanov.decompose.ComponentContext
import com.beknur.tanda.feature.home.DefaultHomeComponent


class DefaultFavoritesComponent(
	context: ComponentContext
) : FavoritesComponent {


	class Factory(
	) {
		fun create(
			context: ComponentContext
		) = DefaultFavoritesComponent(
			context
		)
	}
}