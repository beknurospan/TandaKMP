package com.beknur.tanda.feature.home

import com.arkivanov.decompose.ComponentContext
import com.beknur.tanda.feature.auth.AuthComponent
import com.beknur.tanda.feature.auth.DefaultAuthComponent

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