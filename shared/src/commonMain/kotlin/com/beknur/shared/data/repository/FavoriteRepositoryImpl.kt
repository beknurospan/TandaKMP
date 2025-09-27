package com.beknur.shared.data.repository

import com.beknur.shared.data.mappers.toFavorite
import com.beknur.shared.data.mappers.toFavoriteEntity
import com.beknur.shared.database.dao.FavoriteDao
import com.beknur.shared.domain.model.Favorite
import com.beknur.shared.domain.model.ProductDetail
import com.beknur.shared.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class FavoriteRepositoryImpl(
	private val favoriteDao: FavoriteDao
) : FavoriteRepository {
	override fun getFavorites(): Flow<List<Favorite>> {
		return favoriteDao.getFavoriteEntities()
			.map { entities -> entities.map { favoriteEntity -> favoriteEntity.toFavorite() } }
	}

	override suspend  fun addFavorite(favorite: Favorite) {
		favoriteDao.insertFavoriteEntity(favorite.toFavoriteEntity())
	}

	override suspend  fun removeFavorite(favorite: Favorite) {
		favoriteDao.deleteFavoriteEntity(favorite.productId)
	}

	override suspend  fun updateFavorite(favorite: Favorite) {
		favoriteDao.updateFavoriteEntity(favorite.toFavoriteEntity())
	}

	override suspend  fun toggleFavorite(productDetail: ProductDetail) {
		if (productDetail.isFavorite) {
			addFavorite(productDetail.toFavorite())
		} else {
			removeFavorite(productDetail.toFavorite())
		}

	}

}