package app.duss.kcommerce.ui.kcommerce.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import app.duss.kcommerce.core.model.Pokemon
import app.duss.kcommerce.data.repository.PokemonRepository
import app.duss.kcommerce.kCommerceDispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class KCommerceStoreFactory(
    private val storeFactory: StoreFactory,
    private val searchValue: String,
): KoinComponent {

    private val pokemonRepository by inject<PokemonRepository>()

    fun create(): KCommerceStore =
        object : KCommerceStore, Store<KCommerceStore.Intent, KCommerceStore.State, Nothing> by storeFactory.create(
            name = "KCommerceStore",
            initialState = KCommerceStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Msg {
        object PokemonListLoading : Msg()
        data class PokemonListLoaded(val pokemonList: List<Pokemon>) : Msg()
        data class PokemonListFailed(val error: String?) : Msg()
        data class SearchValueUpdated(val searchValue: String) : Msg()
        object LastPageLoaded : Msg()
    }

    private inner class ExecutorImpl : CoroutineExecutor<KCommerceStore.Intent, Unit, KCommerceStore.State, Msg, Nothing>(
        kCommerceDispatchers.main) {
        override fun executeAction(action: Unit, getState: () -> KCommerceStore.State) {
            loadPokemonListByPage(page = 0)
        }

        override fun executeIntent(intent: KCommerceStore.Intent, getState: () -> KCommerceStore.State): Unit =
            when (intent) {
                is KCommerceStore.Intent.LoadPokemonListByPage -> loadPokemonListByPage(intent.page, getState().isLastPageLoaded)
                is KCommerceStore.Intent.UpdateSearchValue -> dispatch(Msg.SearchValueUpdated(intent.searchValue))
            }

        private var loadPokemonListByPageJob: Job? = null
        private fun loadPokemonListByPage(
            page: Long,
            isLastPageLoaded: Boolean = false
        ) {
            if (loadPokemonListByPageJob?.isActive == true) return
            if (isLastPageLoaded) return

            loadPokemonListByPageJob = scope.launch {
                dispatch(Msg.PokemonListLoading)

                pokemonRepository
                    .getPokemonList(page)
                    .onSuccess { pokemonList ->
                        if (pokemonList.isEmpty()) {
                            dispatch(Msg.LastPageLoaded)
                        } else {
                            dispatch(Msg.PokemonListLoaded(pokemonList))
                        }
                    }
                    .onFailure { e ->
                        dispatch(Msg.PokemonListFailed(e.message))
                    }
            }
        }
    }

    private object ReducerImpl: Reducer<KCommerceStore.State, Msg> {
        override fun KCommerceStore.State.reduce(msg: Msg): KCommerceStore.State =
            when (msg) {
                is Msg.PokemonListLoading -> copy(isLoading = true)
                is Msg.PokemonListLoaded -> KCommerceStore.State(pokemonList = pokemonList + msg.pokemonList)
                is Msg.PokemonListFailed -> copy(error = msg.error)
                is Msg.SearchValueUpdated -> copy(searchValue = msg.searchValue)
                Msg.LastPageLoaded -> copy(isLastPageLoaded = true)
            }
    }
}