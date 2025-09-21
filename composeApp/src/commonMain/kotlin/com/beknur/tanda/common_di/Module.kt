package com.beknur.tanda.common_di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.beknur.tanda.feature.auth.DefaultAuthComponent
import com.beknur.tanda.feature.home.DefaultHomeComponent
import com.beknur.tanda.navigation.DefaultRootComponent
import com.beknur.tanda.navigation.RootStoreFactory
import org.koin.dsl.module

val commonModule = module {

	single<StoreFactory> { DefaultStoreFactory() }
	single<RootStoreFactory> { RootStoreFactory(get()) }
	single<DefaultAuthComponent.Factory> { DefaultAuthComponent.Factory() }
	single<DefaultRootComponent.Factory>
	{
		DefaultRootComponent
			.Factory(
				get(),
				get(),
				get()
			)
	}
	single<DefaultHomeComponent.Factory> { DefaultHomeComponent.Factory() }

}