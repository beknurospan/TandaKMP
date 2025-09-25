package com.beknur.shared.platform

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


actual fun createHttpClient(configure: HttpClientConfig<*>.() -> Unit): HttpClient {
	return HttpClient(CIO){
		install(ContentNegotiation) {
			json(
				Json {
					ignoreUnknownKeys = true
					prettyPrint = true
					isLenient = true
				}
			)
		}
		configure()
	}
}