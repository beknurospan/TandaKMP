package com.beknur.shared.platform

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

expect fun createHttpClient(configure: HttpClientConfig<*>.() -> Unit): HttpClient