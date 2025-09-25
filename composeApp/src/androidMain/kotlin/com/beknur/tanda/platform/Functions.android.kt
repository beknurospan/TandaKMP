package com.beknur.tanda.platform

import android.app.Activity
import com.beknur.tanda.platform.ActivityHolder.activity

actual interface AppCloser {
	actual fun closeApp()
}

class AndroidAppCloser() : AppCloser {
	override fun closeApp() {
		val activity = ActivityHolder.activity ?: return
		activity.runOnUiThread {
			activity.finishAffinity()
		}
	}
}

object ActivityHolder {
	var activity: Activity? = null
}