package app.duss.kcommerce.core.database.dao

import app.duss.kcommerce.core.database.PokemonDatabase
import app.duss.kcommerce.kCommerceDispatchers
import appdusskcommerce.PokemonEntity
import kotlinx.coroutines.withContext

class PokemonDao(
    private val pokemonDatabase: PokemonDatabase
) {
    private val query get() = pokemonDatabase.pokemonEntityQueries

    suspend fun selectAllByPage(page: Long) = withContext(kCommerceDispatchers.io) {
        query.selectAllByPage(page = page).executeAsList()
    }

    suspend fun insert(pokemonEntity: PokemonEntity) = withContext(kCommerceDispatchers.io) {
        query.insert(pokemonEntity)
    }
}
