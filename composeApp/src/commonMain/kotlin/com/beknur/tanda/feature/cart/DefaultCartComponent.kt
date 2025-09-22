package com.beknur.tanda.feature.cart

import com.arkivanov.decompose.ComponentContext
import com.beknur.tanda.feature.home.DefaultHomeComponent

class DefaultCartComponent(
	context: ComponentContext
) : CartComponent {


	class Factory(
	) {
		fun create(
			context: ComponentContext
		) = DefaultCartComponent(
			context
		)
	}
}