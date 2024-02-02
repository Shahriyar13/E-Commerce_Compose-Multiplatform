package app.duss.kcommerce.ui

import androidx.compose.runtime.Composable
import app.duss.kcommerce.ui.root.RootComponent
import app.duss.kcommerce.ui.root.RootContent

@Composable
internal fun ContentView(
    component: RootComponent,
) {
    RootContent(component)
}