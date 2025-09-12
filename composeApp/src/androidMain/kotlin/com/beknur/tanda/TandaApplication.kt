package com.beknur.tanda

import android.app.Application
import com.beknur.shared.di.initKoin
import com.beknur.tanda.platform.platformModule
import org.koin.android.ext.koin.androidContext

class TandaApplication : Application() {
	override fun onCreate() {
		super.onCreate()

		initKoin(config = {
			androidContext(this@TandaApplication)
		}, platformModule = platformModule)

	}
}


