package com.beknur.tanda.feature.profile

import com.arkivanov.decompose.ComponentContext
import com.beknur.tanda.feature.home.DefaultHomeComponent
import com.beknur.tanda.feature.home.HomeComponent

class DefaultProfileComponent(
	context: ComponentContext
) : ProfileComponent {


	class Factory(
	) {
		fun create(
			context: ComponentContext
		) = DefaultProfileComponent(
			context
		)
	}
}