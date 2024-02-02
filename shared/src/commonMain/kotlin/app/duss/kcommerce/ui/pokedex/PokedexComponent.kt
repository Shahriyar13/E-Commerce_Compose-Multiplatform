package app.duss.kcommerce.ui.kcommerce

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import app.duss.kcommerce.ui.kcommerce.store.KCommerceStore
import app.duss.kcommerce.ui.kcommerce.store.KCommerceStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class PokedexComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    searchValue: String,
    private val output: (Output) -> Unit
): ComponentContext by componentContext {

    private val kcommerceStore =
        instanceKeeper.getStore {
            KCommerceStoreFactory(
                storeFactory = storeFactory,
                searchValue = searchValue,
            ).create()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<KCommerceStore.State> = kcommerceStore.stateFlow

    fun onEvent(event: KCommerceStore.Intent) {
        kcommerceStore.accept(event)
    }

    fun onOutput(output: Output) {
        output(output)
    }

    sealed class Output {
        object NavigateBack : Output()
        data class NavigateToDetails(val name: String) : Output()
    }

}