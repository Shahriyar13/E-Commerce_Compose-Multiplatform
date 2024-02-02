package app.duss.kcommerce.core.network.model

import app.duss.kcommerce.core.model.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
  val count: Int,
  val next: String?,
  val previous: String?,
  val results: List<Pokemon>
)
