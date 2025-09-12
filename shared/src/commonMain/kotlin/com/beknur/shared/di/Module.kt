package com.beknur.shared.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null, platformModule: Module) =
	startKoin {
		config?.invoke(this)
		modules(
			sharedModule,
			platformModule,
		)
	}


val sharedModule = module {

}
expect val platformModule: Module