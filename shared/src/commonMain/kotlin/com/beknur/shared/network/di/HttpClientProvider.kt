package com.beknur.shared.network.di


import com.beknur.shared.network.api.DgisApi
import com.beknur.shared.network.api.DgisApiImpl
import com.beknur.shared.network.api.ProductApi
import com.beknur.shared.network.api.ProductApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module


val networkModule = module {


	single(named("DgisClient")) {
		createHttpClient(engine = get()) {
			defaultRequest {
				url {
					protocol = URLProtocol.HTTPS
					host = "catalog.api.2gis.com"
					encodedPath = "/3.0/"
				}
			}
		}
	}

	single(named("BackendClient")) {
		createHttpClient(engine = get()) {
			defaultRequest {
				url {
					protocol = URLProtocol.HTTP
					host = "10.0.2.2"
					port=3050
					encodedPath="/api/v1/"
				}
			}
		}
	}
	single<ProductApi> {
		ProductApiImpl(get(named("BackendClient")))
	}
	single<DgisApi> {
		DgisApiImpl(get(named("DgisClient")))
	}
}

fun createHttpClient(engine: HttpClientEngine,configure: HttpClientConfig<*>.() -> Unit): HttpClient {
	return HttpClient(engine){
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