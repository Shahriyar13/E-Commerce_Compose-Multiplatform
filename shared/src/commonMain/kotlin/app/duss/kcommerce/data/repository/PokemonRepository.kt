package app.duss.kcommerce.data.repository

import app.duss.kcommerce.core.model.Pokemon
import app.duss.kcommerce.core.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList(page: Long): Result<List<Pokemon>>

    suspend fun getPokemonFlowByName(name: String): Result<PokemonInfo>
    suspend fun getFavoritePokemonListFlow(): Flow<List<Pokemon>>
    suspend fun updatePokemonFavoriteState(name: String, isFavorite: Boolean)

}