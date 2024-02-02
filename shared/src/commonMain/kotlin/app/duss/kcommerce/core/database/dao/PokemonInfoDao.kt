package app.duss.kcommerce.core.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.duss.kcommerce.core.database.PokemonDatabase
import app.duss.kcommerce.kCommerceDispatchers
import appdusskcommerce.PokemonInfoEntity
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PokemonInfoDao(
    private val pokemonDatabase: PokemonDatabase
) {
    private val query get() = pokemonDatabase.pokemonInfoEntityQueries

    suspend fun selectOneByName(name: String) = withContext(kCommerceDispatchers.io) {
        query.selectOneByName(name = name).executeAsOneOrNull()
    }

    suspend fun selectAllFavorite() = withContext(kCommerceDispatchers.io) {
        query.selectAllFavorite().asFlow().map { it.executeAsList() }
    }

    suspend fun insert(pokemonInfoEntity: PokemonInfoEntity) = withContext(kCommerceDispatchers.io) {
        query.insert(pokemonInfoEntity)
    }

    suspend fun updateIsFavorite(name: String, isFavorite: Boolean) = withContext(kCommerceDispatchers.io) {
        query.updateIsFavorite(
            isFavorite = if (isFavorite) 1 else 0,
            name = name
        )
    }
}
