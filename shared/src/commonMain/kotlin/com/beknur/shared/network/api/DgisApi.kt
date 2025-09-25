package com.beknur.shared.network.api

import com.beknur.shared.network.model.MySearchResultDto
import com.beknur.shared.network.model.TwoGisResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.request
import io.ktor.http.encodedPath


interface DgisApi {
	suspend fun fetchSuggestions(query: String): List<MySearchResultDto>
}

class DgisApiImpl(private val client: HttpClient) : DgisApi {
	override suspend fun fetchSuggestions(query: String): List<MySearchResultDto> {
		val response: TwoGisResponseDto = client.get {
			url { encodedPath = "suggests" }
			parameter("q", query)
			parameter("location", "76.9,43.25")
			parameter("suggest_type", "address")
			parameter("key", "8d657ddd-062a-4e9f-b196-b4d3c5ada981")
		}.also { println(it.request.url) }.body()

		return response.result?.items ?: emptyList()


	}
}


