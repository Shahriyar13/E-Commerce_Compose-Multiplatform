package app.duss.kcommerce.ui.kcommerce

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.duss.kcommerce.ui.kcommerce.components.KCommerceContent

@Composable
internal fun PokedexScreen(component: PokedexComponent) {

    val state by component.state.collectAsState()

    KCommerceContent(
        state = state,
        onEvent = component::onEvent,
        onOutput = component::onOutput
    )

}