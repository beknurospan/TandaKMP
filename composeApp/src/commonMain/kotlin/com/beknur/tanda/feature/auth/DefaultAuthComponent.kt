package com.beknur.tanda.feature.auth

import com.arkivanov.decompose.ComponentContext

class DefaultAuthComponent(
	context: ComponentContext
) : AuthComponent {


	class Factory(
	//rep
	) {
		fun create(
			context: ComponentContext
		) = DefaultAuthComponent(
			context
		)
	}
}