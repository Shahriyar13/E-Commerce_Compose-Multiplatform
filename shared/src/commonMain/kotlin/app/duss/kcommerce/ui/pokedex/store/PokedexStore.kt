package app.duss.kcommerce.ui.kcommerce.store

import com.arkivanov.mvikotlin.core.store.Store
import app.duss.kcommerce.core.model.Pokemon

interface KCommerceStore: Store<KCommerceStore.Intent, KCommerceStore.State, Nothing> {

    sealed class Intent {
        data class LoadPokemonListByPage(val page: Long): Intent()
        data class UpdateSearchValue(val searchValue: String): Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val isLastPageLoaded: Boolean = false,
        val error: String? = null,
        val pokemonList: List<Pokemon> = emptyList(),
        val searchValue: String = "",
    )
}