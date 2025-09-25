package com.beknur.shared.network.di


import com.beknur.shared.network.api.DgisApi
import com.beknur.shared.network.api.DgisApiImpl
import com.beknur.shared.network.api.ProductApi
import com.beknur.shared.network.api.ProductApiImpl
import com.beknur.shared.platform.createHttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import org.koin.core.qualifier.named
import org.koin.dsl.module


val networkModule = module {

	single(named("DgisClient")) {
		createHttpClient {
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
		createHttpClient {
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