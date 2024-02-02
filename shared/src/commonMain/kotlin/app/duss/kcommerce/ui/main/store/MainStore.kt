package app.duss.kcommerce.ui.main.store

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.Store
import app.duss.kcommerce.core.model.Pokemon

interface MainStore: Store<MainStore.Intent, MainStore.State, Nothing> {

    sealed class Intent {
        data class InputPokemonSearch(val search: String): Intent()
    }

    data class State(
        val search: String = "",
    )

}