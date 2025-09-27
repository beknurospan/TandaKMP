package com.beknur.tanda.common_di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.beknur.tanda.feature.auth.AuthStoreFactory
import com.beknur.tanda.feature.auth.DefaultAuthComponent
import com.beknur.tanda.feature.cart.DefaultCartComponent
import com.beknur.tanda.feature.catalog.DefaultCatalogComponent
import com.beknur.tanda.feature.favorites.DefaultFavoritesComponent
import com.beknur.tanda.feature.home.DefaultHomeComponent
import com.beknur.tanda.feature.profile.DefaultProfileComponent
import com.beknur.tanda.navigation.root.DefaultRootComponent
import com.beknur.tanda.navigation.root.RootStoreFactory
import org.koin.dsl.module

val commonModule = module {

	single<StoreFactory> { DefaultStoreFactory() }
	single<RootStoreFactory> { RootStoreFactory(get()) }
	single<AuthStoreFactory> { AuthStoreFactory(get()) }
	single<DefaultAuthComponent.Factory> { DefaultAuthComponent.Factory(get()) }
	single<DefaultHomeComponent.Factory> { DefaultHomeComponent.Factory() }
	single<DefaultCartComponent.Factory> { DefaultCartComponent.Factory() }
	single<DefaultCatalogComponent.Factory> { DefaultCatalogComponent.Factory() }
	single<DefaultFavoritesComponent.Factory> { DefaultFavoritesComponent.Factory() }
	single<DefaultProfileComponent.Factory> { DefaultProfileComponent.Factory() }
	single<DefaultRootComponent.Factory>
	{
		DefaultRootComponent
			.Factory(
				get(),
				get(),
				get(),
				get(),
				get(),
				get(),
				get()
			)
	}



}