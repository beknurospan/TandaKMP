package com.beknur.tanda.feature.home

import com.arkivanov.decompose.ComponentContext

class DefaultHomeComponent(
	context: ComponentContext
) : HomeComponent {


	class Factory(
	) {
		fun create(
			context: ComponentContext
		) = DefaultHomeComponent(
			context
		)
	}
}