package com.beknur.tanda.platform

import android.util.Log

actual fun logger(message: String) {
	Log.d("TandaLogAnd",message)
}