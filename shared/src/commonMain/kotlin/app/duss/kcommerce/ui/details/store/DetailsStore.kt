package app.duss.kcommerce.ui.details.store

import com.arkivanov.mvikotlin.core.store.Store
import app.duss.kcommerce.core.model.PokemonInfo

interface DetailsStore: Store<DetailsStore.Intent, DetailsStore.State, Nothing> {

    sealed class Intent {
        data class UpdatePokemonFavoriteState(val isFavorite: Boolean): Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val pokemonInfo: PokemonInfo? = null
    )

}