package com.beknur.shared.domain.repository

import com.beknur.shared.domain.model.Favorite
import com.beknur.shared.domain.model.ProductDetail
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
	fun getFavorites(): Flow<List<Favorite>>
	suspend fun addFavorite(favorite: Favorite)
	suspend fun removeFavorite(favorite: Favorite)
	suspend fun updateFavorite(favorite: Favorite)
	suspend fun toggleFavorite(productDetail: ProductDetail)
}