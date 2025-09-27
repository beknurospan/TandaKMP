package com.beknur.shared.data.mappers

import com.beknur.shared.database.model.AddressEntity
import com.beknur.shared.database.model.CartEntity
import com.beknur.shared.database.model.FavoriteEntity
import com.beknur.shared.domain.model.Address
import com.beknur.shared.domain.model.CartProduct
import com.beknur.shared.domain.model.Characteristic
import com.beknur.shared.domain.model.Favorite
import com.beknur.shared.domain.model.FilterAttribute
import com.beknur.shared.domain.model.FilterParams
import com.beknur.shared.domain.model.MySearchResult
import com.beknur.shared.domain.model.OtherColor
import com.beknur.shared.domain.model.Params
import com.beknur.shared.domain.model.PriceRange
import com.beknur.shared.domain.model.Product
import com.beknur.shared.domain.model.ProductCategory
import com.beknur.shared.domain.model.ProductDetail
import com.beknur.shared.domain.model.ProductType
import com.beknur.shared.domain.model.ProductVariants
import com.beknur.shared.domain.model.SizeItem
import com.beknur.shared.network.model.CharacteristicDto
import com.beknur.shared.network.model.FilterAttributeDto
import com.beknur.shared.network.model.FilterParamsDto
import com.beknur.shared.network.model.MySearchResultDto
import com.beknur.shared.network.model.OtherColorDto
import com.beknur.shared.network.model.ParamDto
import com.beknur.shared.network.model.PriceRangeDto
import com.beknur.shared.network.model.ProductCategoryDto
import com.beknur.shared.network.model.ProductDetailDto
import com.beknur.shared.network.model.ProductDto
import com.beknur.shared.network.model.ProductTypesDto
import com.beknur.shared.network.model.ProductVariantDto
import com.beknur.shared.network.model.SizeItemDto

fun ProductDto.toProduct()=
	Product(
		productId = productId,
		skuId = skuId,
		price = pricePerOne,
		size = size,
		rating = rating,
		name = name,
		img = image,
		brandName = brandName
	)

fun ProductDetail.toFavoriteEntity()=
	FavoriteEntity(
		productId = productId,
		name = name,
		brandName = brandName,
		sizes = sizes.map { it.size },
		price = price,
		img = imageList.first()
	)

fun CartEntity.toCartProduct()=
	CartProduct(
		productId = productId,
		skuId = skuId,
		isSelected = isSelected,
		detailText = detailText,
		brandName = brandName,
		size = size,
		pricePerOne = pricePerOne,
		count = count
	)
fun CartProduct.toCartEntity()=
	CartEntity(
		productId = productId,
		skuId=skuId,
		isSelected = isSelected,
		detailText = detailText,
		brandName = brandName,
		size = size,
		pricePerOne = pricePerOne,
		count = count
	)

fun ProductTypesDto.toProductType()=
	ProductType(
		variants = variants.map { it.toProductVariants() }
	)

fun ProductVariantDto.toProductVariants()=
	ProductVariants(
		skuId = skuId,
		size = size
	)
fun ProductDto.toCartProduct()=
	CartProduct(
		isSelected = true,
		productId = productId,
		skuId = skuId,
		detailText = name,
		brandName = brandName,
		size = size.toString(),
		pricePerOne = pricePerOne.toInt()
	)
fun Product.toCartProduct()=
	CartProduct(
		isSelected = true,
		productId = productId,
		skuId = skuId,
		detailText = name,
		brandName = brandName,
		size = size.toString(),
		pricePerOne = price.toInt()
	)
fun MySearchResultDto.toMySearchResult()=
	MySearchResult(
		name = addressName ?: "",
		fullName = fullName ?: ""
	)

fun AddressEntity.toAddress()=

	Address(
		id = id,
		address = address,
		apartment = apartment,
		entrance = entrance,
		floor = floor
	)

fun Address.toAddressEntity()=
	AddressEntity(
		address = address,
		apartment = apartment,
		entrance = entrance,
		floor = floor
	)
fun ProductCategoryDto.toProductCategory()=
	ProductCategory(
		productId = productId,
		skuId = skuId,
		price = price,
		sizes = sizes,
		rating = rating,
		name = name,
		img = img,
		brandName = brandName
	)


fun FilterParamsDto.toFilterParams() =
	FilterParams(
		category = category ?: throw IllegalArgumentException("Category is required"),
		sizeAttribute = sizeAttribute?.toFilterAttribute()
			?: throw IllegalArgumentException("Size attribute is required"),
		brandAttribute = brandAttribute?.toFilterAttribute()
			?: throw IllegalArgumentException("Brand attribute is required"),
		priceRange = priceRangeDto?.toPriceRange()
			?: throw IllegalArgumentException("Price range is required"),
		filterAttributes = filterAttributes?.map { it.toFilterAttribute() } ?: emptyList(),
	)


fun FilterAttributeDto.toFilterAttribute()=
	FilterAttribute(
		id = id,
		nameAttribute = name,
		params = parameters.map { it.toParams() }.toSet()
	)
fun ParamDto.toParams()=
	Params(
		id = id,
		name = name
	)
fun PriceRangeDto.toPriceRange()=
	PriceRange(
		min = min,
		max = max
	)

fun FilterAttribute.toFilterAttributeDto()=
	FilterAttributeDto(
		id = id,
		name = nameAttribute,
		parameters = params.map { it.toParamDto() }
	)
fun Params.toParamDto()=
	ParamDto(
		id = id,
		name = name
	)
fun PriceRange.toPriceRangeDto()=
	PriceRangeDto(
		min = min,
		max = max
	)

fun OtherColorDto.toOtherColor()=
	OtherColor(
		color = colorName,
		image = imageUrl
	)
fun CharacteristicDto.toCharacteristic()=
	Characteristic(
		id = characteristicId,
		name = characteristicName,
		definition = characteristicValue
	)

fun SizeItemDto.toSizeItem()=
	SizeItem(
		size = size,
		isAvailable = isAvailable,
		count = count,
		skuId = skuId
	)
fun ProductDetailDto.toProductDetail()=
	ProductDetail(
		productId =productId,
		price = price,
		sizes = sizes.map { it.toSizeItem() },
		rating = rating,
		name = name,
		brandName = brandName,
		imageList = imageList,
		isFavorite = isFavorite,
		otherColors = otherColors.map { it.toOtherColor() },
		characteristics = characteristics.map { it.toCharacteristic() }
	)
fun FavoriteEntity.toFavorite()=
	Favorite(
		productId = productId,
		name = name,
		brandName = brandName,
		sizes = sizes,
		price = price,
		img = img
	)
fun ProductDetail.toFavorite()=
	Favorite(
		productId = productId,
		name = name,
		brandName = brandName,
		sizes = sizes.map { it.size },
		price = price,
		img = imageList.first()
	)
fun Favorite.toFavoriteEntity()=
	FavoriteEntity(
		productId = productId,
		name = name,
		brandName = brandName,
		sizes = sizes,
		price = price,
		img = img
	)