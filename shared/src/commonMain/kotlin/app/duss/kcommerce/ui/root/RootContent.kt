package app.duss.kcommerce.ui.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import app.duss.kcommerce.ui.comingsoon.ComingSoonScreen
import app.duss.kcommerce.ui.details.DetailsScreen
import app.duss.kcommerce.ui.favorite.FavoriteScreen
import app.duss.kcommerce.ui.main.MainScreen
import app.duss.kcommerce.ui.kcommerce.PokedexScreen

@Composable
internal fun RootContent(component: RootComponent) {
    Children(
        stack = component.childStack,
        animation = stackAnimation(fade()),
    ) {
        when(val child = it.instance) {
            is RootComponent.Child.Main -> MainScreen(child.component)
            is RootComponent.Child.Pokedex -> PokedexScreen(child.component)
            is RootComponent.Child.Favorite -> FavoriteScreen(child.component)
            is RootComponent.Child.Details -> DetailsScreen(child.component)
            is RootComponent.Child.ComingSoon -> ComingSoonScreen(child.component)
        }
    }
}