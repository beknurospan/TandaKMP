package com.beknur.tanda.feature.catalog

import com.arkivanov.decompose.ComponentContext
import com.beknur.tanda.feature.home.DefaultHomeComponent

class DefaultCatalogComponent(
	context: ComponentContext
) : CatalogComponent {


	class Factory(
	) {
		fun create(
			context: ComponentContext
		) = DefaultCatalogComponent(
			context
		)
	}
}