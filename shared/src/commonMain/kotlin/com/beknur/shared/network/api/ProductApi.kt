package com.beknur.shared.network.api


import com.beknur.shared.network.model.FilterParamsDto
import com.beknur.shared.network.model.ProductCategoryDto
import com.beknur.shared.network.model.ProductDetailDto
import com.beknur.shared.network.model.ProductDto
import com.beknur.shared.network.model.ProductTypesDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.encodedPath

interface ProductApi {
	suspend fun getProductType(id:Int): ProductTypesDto
	suspend fun getProduct(id:Int,skuId: Int): ProductDto
	suspend fun getProductsByCategory(productCategory: String):List<ProductCategoryDto>
	suspend fun getFiltersForCategory(filterCategory: String): FilterParamsDto
	suspend fun getProductDetail(id: Int,skuId: Int): ProductDetailDto
}


class ProductApiImpl(private val client: HttpClient) : ProductApi {
	override suspend fun getProductType(id:Int): ProductTypesDto {
		val response: ProductTypesDto = client.get {
			url {
				encodedPath="product/variants"
			}
			parameter("id",id)

		}.body()
		return response
	}

	override suspend fun getProduct(id: Int,skuId:Int): ProductDto {
		val response: ProductDto = client.get {
			url{
				encodedPath="product"
			}
			parameter("id",id)
			parameter("sku_id",skuId)
		}.body()
		return response
	}
	override suspend fun getProductsByCategory(productCategory: String):List<ProductCategoryDto>{
		val response: List<ProductCategoryDto> =client.get {
			url{
				encodedPath="products"
				parameter("product_category",productCategory)
			}
		}.body()
		return response
	}

	override suspend fun getFiltersForCategory(filterCategory: String) :FilterParamsDto{
		val response: FilterParamsDto =client.get {
			url{
				encodedPath="filters"
			}
			parameter("filter_params",filterCategory)
		}.body()
		return response
	}

	override suspend fun getProductDetail(
		id: Int,
		skuId: Int
	): ProductDetailDto {
		val response: ProductDetailDto =client.get {
			url{
				encodedPath="products/detail"
			}
			parameter("id",id)
			parameter("sku_id",skuId)
		}.body()
		return response
	}


}





