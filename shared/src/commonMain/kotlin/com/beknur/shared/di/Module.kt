package com.beknur.shared.di

import com.beknur.shared.data.di.dataModule
import com.beknur.shared.database.di.databaseModule
import com.beknur.shared.datastore.di.dataStoreModule
import com.beknur.shared.network.di.networkModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null, platformModule: Module, commonModule: Module) =
	startKoin {
		config?.invoke(this)
		modules(
			networkModule,
			databaseModule,
			dataStoreModule,
			dataModule,
			platformModule,
			commonModule,
		)
	}






