package com.beknur.shared.data.di

import com.beknur.shared.data.repository.AddressRepositoryImpl
import com.beknur.shared.data.repository.CartRepositoryImpl
import com.beknur.shared.data.repository.FavoriteRepositoryImpl
import com.beknur.shared.data.repository.ProductRepositoryImpl
import com.beknur.shared.data.repository.UserDataRepositoryImpl
import com.beknur.shared.domain.repository.AddressRepository
import com.beknur.shared.domain.repository.CartRepository
import com.beknur.shared.domain.repository.FavoriteRepository
import com.beknur.shared.domain.repository.ProductRepository
import com.beknur.shared.domain.repository.UserDataRepository
import org.koin.dsl.module

val dataModule=module{
	single<CartRepository>{ CartRepositoryImpl(get(), get()) }
	single<UserDataRepository>{ UserDataRepositoryImpl(get(), get()) }
	single<AddressRepository>{ AddressRepositoryImpl(get()) }
	single<ProductRepository>{ ProductRepositoryImpl(get()) }
	single<FavoriteRepository>{ FavoriteRepositoryImpl(get()) }
}