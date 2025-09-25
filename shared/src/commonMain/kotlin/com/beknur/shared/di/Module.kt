package com.beknur.shared.di

import com.beknur.shared.network.di.networkModule
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null, platformModule: Module, commonModule: Module) =
	startKoin {
		config?.invoke(this)
		modules(
			sharedModule,
			networkModule,
			platformModule,
			commonModule,
		)
	}


val sharedModule = module {

}



